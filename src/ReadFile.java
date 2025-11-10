import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<String> loadWordList(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.toLowerCase().split("\\s+")) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }
        return words;
    }

    public static FileStats readFile(String filePath, ArrayList<String> stopWords, ArrayList<String> posWords, ArrayList<String> negWords) {
        FileStats stats = new FileStats();
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String clean = line.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();
                String[] parts = clean.split("\\s+");
                for (String w : parts) {
                    if (!w.isEmpty() && !stopWords.contains(w)) {
                        words.add(w);
                        if (posWords.contains(w)) stats.posCount++;
                        if (negWords.contains(w)) stats.negCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        stats.totalWords = words.size();

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

        stats.uniqueWords = uniqueWords.size();
        stats.topWord = uniqueWords.isEmpty() ? "" : uniqueWords.get(0);
        stats.topCount = counts.isEmpty() ? 0 : counts.get(0);
        stats.rankedWords = uniqueWords;
        stats.rankedCounts = counts;

        System.out.println("\n--- " + filePath.substring(filePath.lastIndexOf("//") + 1) + " ---");
        System.out.println("Total words: " + stats.totalWords);
        System.out.println("Unique words: " + stats.uniqueWords);
        System.out.println("Most frequent word: " + stats.topWord + " (" + stats.topCount + " times)");
        System.out.println("\nTop 10 repeated words:");
        for (int i = 0; i < Math.min(10, stats.rankedWords.size()); i++) {
            System.out.println(stats.rankedWords.get(i) + ": " + stats.rankedCounts.get(i));
        }

        return stats;
    }


    public static void compareVocabulary(String name1, FileStats stats1, String name2, FileStats stats2) {
        if (stats1.uniqueWords > stats2.uniqueWords) {
            System.out.println(name1 + " has richer vocabulary than " + name2 + " (" + stats1.uniqueWords + " vs " + stats2.uniqueWords + ")");
        } else if (stats2.uniqueWords > stats1.uniqueWords) {
            System.out.println(name2 + " has richer vocabulary than " + name1 + " (" + stats2.uniqueWords + " vs " + stats1.uniqueWords + ")");
        } else {
            System.out.println(name1 + " and " + name2 + " have equal vocabulary richness (" + stats1.uniqueWords + ")");
        }
    }

    public static void printSentiment(String fileName, FileStats stats) {
        String sentiment = "Neutral";
        if (stats.posCount > stats.negCount) sentiment = "Positive";
        else if (stats.negCount > stats.posCount) sentiment = "Negative";

        System.out.println(fileName + " sentiment: " + sentiment + " (Positive words: " + stats.posCount + ", Negative words: " + stats.negCount + ")");
    }
}
