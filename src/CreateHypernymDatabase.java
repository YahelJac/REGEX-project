//208384420

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * in this class, the program scan the files and fill the database by the information.
 */
public class CreateHypernymDatabase {
    /**
     * the main method. run the files.
     *
     * @param args the path to the file.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {

        Database database = new Database();
        String pathToFiles = args[0];
        String pathToOutPut = args[1];
        FileInputStream inputStream = null;
        Scanner scanner = null;
        File file = new File(pathToFiles);
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            inputStream = new FileInputStream(fileList[i]);
            scanner = new Scanner(inputStream, "UTF-8");
            scanner.useDelimiter("[\\n]");

            fillDatabase(database, scanner);
            inputStream.close();
            scanner.close();


        }


        writeToFile(database, pathToOutPut);

    }

    /**
     * in this function the program write in a new file that its creat.
     *
     * @param database     the data that need to be in the file.
     * @param pathToOutput the path towhere the output need to br in
     */
    public static void writeToFile(Database database, String pathToOutput) {
        try {
            FileWriter myWriter = new FileWriter(pathToOutput);

            for (Map.Entry<String, TreeMap<String, Integer>> hypernymy : database.getHupernymMap().entrySet()) {
                if (hypernymy.getValue().size() > 2) {
                    TreeMap<String, Integer> sortedTree = database.sortTree(hypernymy.getValue());
                    myWriter.write(hypernymy.getKey().toString() + ": ");
                    int counter = 1;
                    for (Map.Entry<String, Integer> wordMap : sortedTree.entrySet()) {

                        if (counter != hypernymy.getValue().size()) {
                            myWriter.write(wordMap.getKey().toString() + " ("
                                    + wordMap.getValue() + "), ");
                        } else {
                            myWriter.write(wordMap.getKey().toString() + " ("
                                    + wordMap.getValue() + ")");
                        }
                        counter++;


                    }
                    myWriter.write("\n");


                }

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * this function fill the database by the files information.
     *
     * @param database the database that need to be filed
     * @param scanner  the scanner of the files
     */
    public static void fillDatabase(Database database, Scanner scanner) {
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