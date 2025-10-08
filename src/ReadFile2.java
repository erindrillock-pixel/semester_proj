import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile2 {
    public static void main(String[] args) {
        // Call readFile() for each file
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt");
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt");
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt");
    }

    public static void readFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            System.out.println("Reading file: " + filePath);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String noPunct = line.replaceAll("[^\\sa-zA-Z0-9]", "");
                String[] words = noPunct.split("\\s+");
                ArrayList<String> wordList = new ArrayList<>();
                for (String word : words) {
                    if (!word.isEmpty()) { // avoid adding empty strings
                        wordList.add(word.toLowerCase());
                    }
                }
                System.out.println(wordList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading: " + filePath);
            e.printStackTrace();
        }
    }
}