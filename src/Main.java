import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        String positivePath = "/Users/ErinDrillock/Programming/Project files/positive-words.txt";
        String negativePath = "/Users/ErinDrillock/Programming/Project files/negative-words.txt";
        ArrayList<String> stopWords = ReadFile.loadWordList(stopWordsPath);
        ArrayList<String> posWords = ReadFile.loadWordList(positivePath);
        ArrayList<String> negWords = ReadFile.loadWordList(negativePath);
/*
        System.out.println("\nTopic 1: ");
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt", stopWords, posWords, negWords);
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt", stopWords, posWords, negWords);
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt", stopWords, posWords, negWords);

        System.out.println("\nTopic 2: ");
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt", stopWords, posWords, negWords);
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt", stopWords, posWords, negWords);
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt", stopWords, posWords, negWords);

        System.out.println("\nTopic 3: ");
        FileStats stats1 = readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/wine.txt", stopWords, posWords, negWords);
        FileStats stats2 = readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/rats.txt", stopWords, posWords, negWords);
        FileStats stats3 = readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/banana.txt", stopWords, posWords, negWords);

        System.out.println("\n--- Vocabulary Richness ---");
        compareVocabulary("wine.txt", stats1, "wine.txt", stats2);
        compareVocabulary("rats.txt", stats1, "rats.txt", stats3);
        compareVocabulary("banana.txt", stats2, "banana.txt", stats3);

        System.out.println("\n--- Sentiment ---");
        printSentiment("wine.txt", stats1);
        printSentiment("rats", stats2);
        printSentiment("banana.txt", stats3);
*/
        System.out.println("\nTopic 1: ");
        FileStats stats1 = ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt", stopWords, posWords, negWords);
        FileStats stats2 = ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt", stopWords, posWords, negWords);
        FileStats stats3 = ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt", stopWords, posWords, negWords);

        System.out.println("\n--- Vocabulary Richness ---");
        ReadFile.compareVocabulary("Article 1_ train.txt", stats1, "Article 1_ train.txt", stats2);
        ReadFile.compareVocabulary("Article 2_ castle.txt", stats1, "Article 2_ castle.txt", stats3);
        ReadFile.compareVocabulary("Article 3_ roasted meat.txt", stats2, "Article 3_ roasted meat.txt", stats3);

        System.out.println("\n--- Sentiment ---");
        ReadFile.printSentiment("Article 1_ train.txt", stats1);
        ReadFile.printSentiment("Article 2_ castle.tx", stats2);
        ReadFile.printSentiment("Article 3_ roasted meat.txt", stats3);
    }
}