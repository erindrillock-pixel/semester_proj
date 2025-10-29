import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile3 {
    public static void main(String[] args) {
        String stopWordsPath = "C://Users//laniy//Downloads//CSPC 2231 Lab 2025/stopwords.txt";
        ArrayList<String> stopWords = loadStopWords(stopWordsPath);

        readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/wine.txt", stopWords);
        readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/rats.txt", stopWords);
        readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/banana.txt", stopWords);
    }

    public static ArrayList<String> loadStopWords(String filePath) {
        ArrayList<String> stopWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.toLowerCase().split("\\s+")) {
                    if (!word.isEmpty()) {
                        stopWords.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading stop words file: " + e.getMessage());
        }
        return stopWords;
    }

    public static void readFile(String filePath, ArrayList<String> stopWords) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\nReading file: " + filePath);
            FileStats stats = analyzeFile(br, stopWords);

            System.out.println("--- " + filePath.substring(filePath.lastIndexOf("/") + 1) + " ---");
            System.out.println("Total words: " + stats.totalWords);
            System.out.println("Unique words: " + stats.uniqueWords);
            System.out.println("Most frequent word: " + stats.topWord + " (" + stats.topCount + " times)");
            System.out.println("\nWords ranked by frequency:\n");
            for (int i = 0; i < stats.rankedWords.size(); i++) {
                System.out.println(stats.rankedWords.get(i) + ": " + stats.rankedCounts.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    public static FileStats analyzeFile(BufferedReader br, ArrayList<String> stopWords) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String clean = line.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();
            String[] parts = clean.split("\\s+");
            for (String w : parts) {
                if (!w.isEmpty() && !stopWords.contains(w)) {
                    words.add(w);
                }
            }
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
                    // Swap counts
                    int tempCount = counts.get(j);
                    counts.set(j, counts.get(j + 1));
                    counts.set(j + 1, tempCount);

                    // Swap corresponding words
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
