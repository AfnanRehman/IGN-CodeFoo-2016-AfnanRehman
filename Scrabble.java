
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

        // Importing file and adding contents to an array
        String fileName = "words.txt";
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            words.add(input.next());
        }

        // Enter the sequence of letters to unscramble
        System.out.print("Enter the letters to unscramble: ");
        String scramble = in.nextLine();
        char[] scrambled = scramble.toCharArray();

        // Compare each word in the dictionary to the given letters
        // using letter frequency tables
        iterator(scrambled);

        // Print the best word available and it's score
        System.out.println("");
        System.out.println("Word: " + finalWord);
        System.out.println("Score: " + score);

        input.close();
        in.close();
    }

    /*
     * This method will use frequency tables to compare each word from
     * dictionary with the given letters to find a match of any length. If a
     * match is found it is scored using the scoring method.
     */
    public static void iterator(char[] scrambled) {
        ArrayList<String> foundWord = new ArrayList<String>();

        /*
         * Create a frequency table showing the frequency at which each letter
         * in the array occurs. that way the word will only be counted if the
         * right number of each letter is present in the given letter string.
         */
        int[] array_characters = new int[26];
        for (int i = 0; i < scrambled.length; i++) {
            char c = Character.toLowerCase(scrambled[i]);
            array_characters[c - 'a']++;
        }

        for (int j = 0; j < words.size(); j++) {
            String fWord = words.get(j);
            int[] tmp = new int[26];
            System.arraycopy(array_characters, 0, tmp, 0, 26);
            char[] w = fWord.toCharArray();
            int found = 1;
            for (int i = 0; i < fWord.length(); i++) {
                if (tmp[w[i] - 'a'] < 1) {
                    found = 0;
                    break;
                } else {
                    tmp[w[i] - 'a']--;
                }
            }
            // If the word is found, print it and  add to an arrayList.
            if (found == 1) {
                System.out.println(fWord + " Word Found");
                foundWord.add(fWord);
                // Score the new word and determine if it is the best one
                score(foundWord.get(foundWord.size() - 1));
            } //else {
              //System.out.println(fWord + " Word Not Found");
              //}

        }

    }

    /*
     * Score method will determine the score of the current scrabble word and if
     * the score is higher than the previous highest scoring word it will
     * replace that word.
     */
    private static void score(String word) {
        int score2 = 0;
        char[] c = word.toCharArray();
        // Tallies points for each letter in the array using standard Scrabble point system
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'q' || c[i] == 'z') {
                score2 += 10;
            } else if (c[i] == 'd' || c[i] == 'g') {
                score2 += 2;
            } else if (c[i] == 'b' || c[i] == 'c' || c[i] == 'm'
                    || c[i] == 'p') {
                score2 += 3;
            } else if (c[i] == 'f' || c[i] == 'h' || c[i] == 'v' || c[i] == 'w'
                    || c[i] == 'y') {
                score2 += 4;
            } else if (c[i] == 'k') {
                score2 += 5;
            } else if (c[i] == 'j' || c[i] == 'x') {
                score2 += 8;
            } else {
                score2 += 1;
            }
        }
        // If the word found has a best score, replace the previous best word
        if (score2 > score) {
            score = score2;
            finalWord = word;
        }
    }
}
