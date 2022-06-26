package another;

/**
 * This class stores words and their translations in two arrays,
 * and has methods to retrieve them.
 */
import java.io.*;
import java.util.StringTokenizer;

public class WordArray {
    public WordArray(){}

    /** Data members: for storing words. */
    private String[] english;
    private String[] other;

    public WordArray(File file, int numLines) throws IOException {

        // Get the arrays ready
        english = new String[numLines];
        other = new String[numLines];

        // Input the words from the file into the arrays
        BufferedReader buf = new BufferedReader(new FileReader(file));
        for (int i=0; i<numLines; i++) {
            StringTokenizer st = new StringTokenizer(buf.readLine(), ":");
            english[i] = st.nextToken();
            other[i] = st.nextToken();
        }
        buf.close();
    }

    /**
     * Look up a word in English and print its translation.
     * @param word
     */
    public void translateToEnglish(String word) {
        translate(other, english, word);
    }

    /**
     * Look up a word in the other language and print its translation.
     * @param word
     */
    public void translateFromEnglish(String word) {
        translate(english, other, word);
    }

    /**
     * Helper to translate a word from one array to the other. It looks
     * through the "from" array to find the word, and prints out the
     * corresponding one in the "to" array.
     * @param from The array to look in
     * @param to The array to translate to
     * @param word The word to translate
     */
    private static void translate(String[] from, String[] to, String word) {
        for (int i=0; i<from.length; i++)
            if (from[i].equalsIgnoreCase(word)) {
                System.out.println("The translation is: " + to[i]);
                return;
            }
        System.out.println("Sorry, I don't know that word.");
    }

    /**
     * Go through "other" array and quiz user on each one.
     * @param buf The console reader to use
     */
    public void quizSequentialToEnglish(BufferedReader buf) throws IOException {
        quizSeq(other, english, buf);
    }

    /**
     * Go through "english" array and quiz user on each one.
     * @param buf The console reader to use
     */
    public void quizSequentialFromEnglish(BufferedReader buf) throws IOException {
        quizSeq(english, other, buf);
    }

    /**
     * Helper to prompt user for translations from one array to the other.
     * It loops through the entire "from" array and asks for translations
     * of each word. For each one, it either says the answer is correct
     * or tells the correct answer.
     * @param from The array to prompt from
     * @param to The array to get answers from
     * @param buf The console reader to use
     */
    public static void quizSeq(String[] from, String[] to, BufferedReader buf)
            throws IOException {
        for (int i=0; i<from.length; i++) {
            System.out.print("Translate \"" + from[i] + "\": ");
            if (buf.readLine().equalsIgnoreCase(to[i]))
                System.out.println("Correct!");
            else
                System.out.println("Incorrect. Answer is: " + to[i]);
        }
    }

    /**
     * Quiz the user on a random word in the "other" array.
     * @param buf The console reader to use
     */
    public void quizRandomToEnglish(BufferedReader buf) throws IOException {
        quizRan(other, english, buf);
    }

    /**
     * Quiz the user on a random word in the "english" array.
     * @param buf The console reader to use
     */
    public void quizRandomFromEnglish(BufferedReader buf) throws IOException {
        quizRan(english, other, buf);
    }

    /**
     * Helper to prompt user for a random translation. It calculates a
     * random index in the "from" array, asks for a translation, and
     * either says the answer is correct or tells the correct answer.
     * @param from The array to prompt from
     * @param to The array to get answers from
     * @param buf The console reader to use
     */
    public static void quizRan(String[] from, String[] to, BufferedReader buf)
            throws IOException  {
        int i = (int) (Math.random() * from.length);
        System.out.print("Translate \"" + from[i] + "\": ");
        if (buf.readLine().equalsIgnoreCase(to[i]))
            System.out.println("Correct!");
        else
            System.out.println("Incorrect. Answer is: " + to[i]);
    }
}