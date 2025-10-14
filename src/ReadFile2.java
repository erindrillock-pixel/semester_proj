import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile2 {

    // Small helper class to hold file statistics
    static class FileStats {
        int totalWords;
        int uniqueWords;
        String topWord;
        int topCount;
    }

    public static void main(String[] args) {
        // Path to stopwords file
        String stopWordsPath = "/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/stopwords.txt";

        // Load stop words (silently, not printed)
        ArrayList<String> stopWords = loadStopWords(stopWordsPath);

        // Analyze each article file
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt", stopWords);
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt", stopWords);
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt", stopWords);
    }

    // Load stop words quietly
    public static ArrayList<String> loadStopWords(String filePath) {
        ArrayList<String> stopWords = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNext()) {
                stopWords.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Stop words file not found: " + filePath);
        }
        return stopWords;
    }

    // Reads and analyzes one file
    public static void readFile(String filePath, ArrayList<String> stopWords) {
        File file = new File(filePath);
        try {
            System.out.println("\nReading file: " + filePath);
            FileStats stats = analyzeFile(file, stopWords);

            // Print the basic statistics only
            System.out.println("--- " + file.getName() + " ---");
            System.out.println("Total words: " + stats.totalWords);
            System.out.println("Unique words: " + stats.uniqueWords);
            System.out.println("Most frequent word: " + stats.topWord + " (" + stats.topCount + " times)");

        } catch (Exception e) {
            System.out.println("An error occurred while reading: " + filePath);
            e.printStackTrace();
        }
    }

    // Analyze file and return its statistics
    public static FileStats analyzeFile(File file, ArrayList<String> stopWords) {
        ArrayList<String> words = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String clean = line.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();
                String[] parts = clean.split("\\s+");

                for (String w : parts) {
                    if (!w.isEmpty() && !stopWords.contains(w)) {
                        words.add(w);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + file.getName());
            return null;
        }

        // Count total and unique words
        int totalWords = words.size();
        ArrayList<String> uniqueWords = new ArrayList<>();
        for (String w : words) {
            if (!uniqueWords.contains(w)) {
                uniqueWords.add(w);
            }
        }

        // Count most frequent word
        String topWord = "";
        int topCount = 0;
        for (String w : uniqueWords) {
            int count = 0;
            for (String x : words) {
                if (x.equals(w)) {
                    count++;
                }
            }
            if (count > topCount) {
                topCount = count;
                topWord = w;
            }
        }

        // Create and return stats object
        FileStats stats = new FileStats();
        stats.totalWords = totalWords;
        stats.uniqueWords = uniqueWords.size();
        stats.topWord = topWord;
        stats.topCount = topCount;

        return stats;
    }
}
