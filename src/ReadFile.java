import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
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
    public static ArrayList<String> loadWordList(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNext()) {
                words.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Word list file not found: " + filePath);
        }
        return words;
    }

    public static void readFile(String filePath, ArrayList<String> stopWords, ArrayList<String> posWords, ArrayList<String> negWords, ArrayList<String> words) {
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
            System.out.println("\nTop 10 repeated words:\n");

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
