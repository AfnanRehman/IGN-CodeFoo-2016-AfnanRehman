
/*
 * Program that determines best Scrabble word from a list of letters
 *
 * @author rehman
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scrabble {

    public static ArrayList<String> words = new ArrayList<String>();
    public static int score = 0;
    public static String finalWord = "";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        String fileName = "words.txt";
        File file = new File(fileName);

        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            words.add(input.next());
        }

        System.out.print("Enter the letters to unscramble: ");
        String scramble = in.nextLine();
        char[] scrambled = scramble.toCharArray();
        int len = 5;
        iterate(scrambled, len, new char[len], 0);

        System.out.println("Word: " + finalWord);
        System.out.println("Score: " + score);

        input.close();
        in.close();
    }

    public static void iterate(char[] chars, int len, char[] build, int pos) {
        if (pos == len) {
            String word = new String(build);
            for (int i = 0; i < words.size(); i++) {
                String compare = words.get(i);
                if (word.equals(compare)) {
                    score(word);
                }
            }

            return;
        }

        for (int i = 0; i < chars.length; i++) {
            build[pos] = chars[i];
            iterate(chars, len, build, pos + 1);
        }
    }

    private static void score(String word) {
        if (word.length() > finalWord.length()) {
            finalWord = word;
        }
        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'q' || c[i] == 'z') {
                score += 10;
            } else if (c[i] == 'd' || c[i] == 'g') {
                score += 2;
            } else if (c[i] == 'b' || c[i] == 'c' || c[i] == 'm'
                    || c[i] == 'p') {
                score += 3;
            } else if (c[i] == 'f' || c[i] == 'h' || c[i] == 'v' || c[i] == 'w'
                    || c[i] == 'y') {
                score += 4;
            } else if (c[i] == 'k') {
                score += 5;
            } else if (c[i] == 'j' || c[i] == 'x') {
                score += 8;
            } else {
                score += 1;
            }
        }
    }
}
