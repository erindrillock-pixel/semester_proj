import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ReadFile {

    public static void main(String[] args) {
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        String articlePath1 = "/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt";
        String articlePath2 = "/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt";
        String articlePath3 = "/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt";

        Set<String> stopWords = loadStopWords(stopWordsPath);

        processArticle("Article 1", articlePath1, stopWords);
        processArticle("Article 2", articlePath2, stopWords);
        processArticle("Article 3", articlePath3, stopWords);
    }

    public static void processArticle(String label, String filePath, Set<String> stopWords) {
        ArrayList<String> words = loadWordsFromFile(filePath);
        int totalWords = words.size();

        removeStopWords(words, stopWords);

        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> wordCounts = new ArrayList<>();

        for (String word : words) {
            int index = uniqueWords.indexOf(word);
            if (index != -1) {
                wordCounts.set(index, wordCounts.get(index) + 1);
            } else {
                uniqueWords.add(word);
                wordCounts.add(1);
            }
        }

        for (int i = 0; i < wordCounts.size() - 1; i++) {
            for (int j = 0; j < wordCounts.size() - i - 1; j++) {
                if (wordCounts.get(j) < wordCounts.get(j + 1)) {
                    int tempCount = wordCounts.get(j);
                    wordCounts.set(j, wordCounts.get(j + 1));
                    wordCounts.set(j + 1, tempCount);
                    String tempWord = uniqueWords.get(j);
                    uniqueWords.set(j, uniqueWords.get(j + 1));
                    uniqueWords.set(j + 1, tempWord);
                }
            }
        }

        System.out.println("\n--- " + label + " ---");
        System.out.println("Total words in article: " + totalWords);
        System.out.println("Unique words: " + uniqueWords.size());
        System.out.println("\nWords ranked by frequency:\n");

        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println(uniqueWords.get(i) + ": " + wordCounts.get(i));
        }
    }

    public static void removeStopWords(ArrayList<String> words, Set<String> stopWords) {
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (stopWords.contains(word)) {
                iterator.remove();
            }
        }
    }

    public static Set<String> loadStopWords(String filePath) {
        Set<String> stopWords = new HashSet<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNext()) {
                stopWords.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Stop words file not found: " + filePath);
        }
        return stopWords;
    }

    public static ArrayList<String> loadWordsFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
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
}