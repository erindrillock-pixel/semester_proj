import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFile {
    static class FileStats {
        int totalWords;
        int uniqueWords;
        String topWord;
        int topCount;
    }

    public static void main(String[] args) {
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        String articlePath1 = "/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt";
        String articlePath2 = "/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt";
        String articlePath3 = "/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt";

        // Load stop words
        List<String> stopWords = loadStopWords(stopWordsPath);

        // Process each article
        processArticle("Article 1", articlePath1, stopWords);
        processArticle("Article 2", articlePath2, stopWords);
        processArticle("Article 3", articlePath3, stopWords);
    }

    public static void processArticle(String label, String filePath, List<String> stopWords) {
        List<String> words = loadWordsFromFile(filePath);
        removeStopWords(words, stopWords);
        FileStats stats = analyzeWords(words);

        // Print statistics
        System.out.println("\n--- " + label + " ---");
        System.out.println("Total words: " + stats.totalWords);
        System.out.println("Unique words: " + stats.uniqueWords);
        System.out.println("Most frequent word: " + stats.topWord + " (" + stats.topCount + " times)");
    }

    // Removes stop words using an iterator
    public static void removeStopWords(List<String> words, List<String> stopWords) {
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (stopWords.contains(word)) {
                iterator.remove();
            }
        }
    }

    // Reads stop words from file
    public static List<String> loadStopWords(String filePath) {
        List<String> stopWords = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNext()) {
                stopWords.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Stop words file not found: " + filePath);
        }
        return stopWords;
    }

    // Reads words from an article file
    public static List<String> loadWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().toLowerCase();
                line = line.replaceAll("[^a-z0-9\\s]", " ");
                String[] parts = line.split("\\s+");
                for (String word : parts) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Article file not found: " + filePath);
        }
        return words;
    }

    // Analyze word list and return stats
    public static FileStats analyzeWords(List<String> words) {
        FileStats stats = new FileStats();
        stats.totalWords = words.size();

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        stats.uniqueWords = frequencyMap.size();

        String topWord = "";
        int topCount = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > topCount) {
                topWord = entry.getKey();
                topCount = entry.getValue();
            }
        }

        stats.topWord = topWord;
        stats.topCount = topCount;

        return stats;
    }
}

