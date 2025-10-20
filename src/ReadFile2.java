import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReadFile2 {

    static class FileStats {
        int totalWords;
        int uniqueWords;
        String topWord;
        int topCount;
        ArrayList<String> rankedWords;
        ArrayList<Integer> rankedCounts;
    }

    public static void main(String[] args) {
        String stopWordsPath = "/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/stopwords.txt";
        ArrayList<String> stopWords = loadStopWords(stopWordsPath);

        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt", stopWords);
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt", stopWords);
        readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt", stopWords);
    }

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

    public static void readFile(String filePath, ArrayList<String> stopWords) {
        File file = new File(filePath);
        try {
            System.out.println("\nReading file: " + filePath);
            FileStats stats = analyzeFile(file, stopWords);

            System.out.println("--- " + file.getName() + " ---");
            System.out.println("Total words: " + stats.totalWords);
            System.out.println("Unique words: " + stats.uniqueWords);
            System.out.println("Most frequent word: " + stats.topWord + " (" + stats.topCount + " times)");
            System.out.println("\nWords ranked by frequency:\n");
            for (int i = 0; i < stats.rankedWords.size(); i++) {
                System.out.println(stats.rankedWords.get(i) + ": " + stats.rankedCounts.get(i));
            }

        } catch (Exception e) {
            System.out.println("An error occurred while reading: " + filePath);
            e.printStackTrace();
        }
    }

    public static FileStats analyzeFile(File file, ArrayList<String> stopWords) {
        ArrayList<String> words = new ArrayList<>();

        try (Scanner reader = new Scanner(file)) {
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
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + file.getName());
            return null;
        }

        int totalWords = words.size();
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (String w : words) {
            int index = uniqueWords.indexOf(w);
            if (index != -1) {
                counts.set(index, counts.get(index) + 1);
            } else {
                uniqueWords.add(w);
                counts.add(1);
            }
        }

        // bubble sort by frequency
        for (int i = 0; i < counts.size() - 1; i++) {
            for (int j = 0; j < counts.size() - i - 1; j++) {
                if (counts.get(j) < counts.get(j + 1)) {
                    int tempCount = counts.get(j);
                    counts.set(j, counts.get(j + 1));
                    counts.set(j + 1, tempCount);

                    String tempWord = uniqueWords.get(j);
                    uniqueWords.set(j, uniqueWords.get(j + 1));
                    uniqueWords.set(j + 1, tempWord);
                }
            }
        }

        String topWord = "";
        int topCount = 0;
        if (!uniqueWords.isEmpty()) {
            topWord = uniqueWords.get(0);
            topCount = counts.get(0);
        }

        FileStats stats = new FileStats();
        stats.totalWords = totalWords;
        stats.uniqueWords = uniqueWords.size();
        stats.topWord = topWord;
        stats.topCount = topCount;
        stats.rankedWords = uniqueWords;
        stats.rankedCounts = counts;

        return stats;
    }
}
