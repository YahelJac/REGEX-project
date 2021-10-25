//208384420

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class sort the list by a certain word.
 */
public class DiscoverHypernym {
    /**
     * main method that run the files and print them.
     *
     * @param args gets the path to the files and the word.
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {

        String path = args[0];
        String word = args[1];
        FileInputStream inputStream = null;
        Scanner scanner = null;

        File file = new File(path);
        File[] fileList = file.listFiles();
        Database database = new Database();
        for (int i = 0; i < fileList.length; i++) {

            inputStream = new FileInputStream(fileList[i]);
            scanner = new Scanner(inputStream, "UTF-8");
            scanner.useDelimiter("[\\n]");

            fillDatabase(database, scanner, word);
            inputStream.close();
            scanner.close();


        }
        TreeMap<String, TreeMap<String, Integer>> sorted = database.sortTreeFor2(database.getHupernymMap(), word);

        for (Map.Entry<String, TreeMap<String, Integer>> hypernymy : sorted.entrySet()) {

            for (Map.Entry<String, Integer> wordMap : hypernymy.getValue().entrySet()) {
                System.out.println(hypernymy.getKey() + ": (" + wordMap.getValue() + ")");
            }
        }


    }

    /**
     * fill the database.
     *
     * @param database the database object.
     * @param scanner  scan the files
     * @param word     the certain word
     */
    public static void fillDatabase(Database database, Scanner scanner, String word) {

        PatternsKind patternsKind = new PatternsKind();
        while (scanner.hasNext()) {
            String line = scanner.next();
            for (int i = 1; i < 6; i++) {
                Pattern pattern = Pattern.compile(patternsKind.getPattern(i), Pattern.MULTILINE);


                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {

                    Pattern pattern1 = Pattern.compile("<np>([^<]+)</np>", Pattern.MULTILINE);
                    int j = 2;

                    if (i == 3) {
                        j = 1;
                    }
                    Matcher matcher1 = pattern1.matcher(matcher.group(j));
                    while (matcher1.find()) {

                        if (!matcher1.group(1).equals(word)) {
                            continue;
                        }

                        if (i == 3) {
                            database.addWord(matcher1.group(1), matcher.group(2));
                        } else {
                            database.addWord(matcher1.group(1), matcher.group(1));
                        }


                    }

                }


            }
        }

    }

}
