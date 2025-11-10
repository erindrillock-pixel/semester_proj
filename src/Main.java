import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        String positivePath = "/Users/ErinDrillock/Programming/Project files/positive-words.txt";
        String negativePath = "/Users/ErinDrillock/Programming/Project files/negative-words.txt";
        ArrayList<String> stopWords = ReadFile.loadWordList(stopWordsPath);
        ArrayList<String> posWords = ReadFile.loadWordList(positivePath);
        ArrayList<String> negWords = ReadFile.loadWordList(negativePath);

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
        ReadFile.printSentiment("Article 2_ castle.txt", stats2);
        ReadFile.printSentiment("Article 3_ roasted meat.txt", stats3);

        System.out.println("\nTopic 2: ");
        FileStats stats4 = ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt", stopWords, posWords, negWords);
        FileStats stats5 = ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt", stopWords, posWords, negWords);
        FileStats stats6 = ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt", stopWords, posWords, negWords);
        System.out.println("\n--- Vocabulary Richness ---");
        ReadFile.compareVocabulary("Weather Topic 3.1.txt", stats4, "Article 1_ train.txt", stats5);
        ReadFile.compareVocabulary("Weather Topic 3.2.txt", stats4, "Article 2_ castle.txt", stats6);
        ReadFile.compareVocabulary("Weather Topic 3.3.txt", stats5, "Article 3_ roasted meat.txt", stats6);
        System.out.println("\n--- Sentiment ---");
        ReadFile.printSentiment("Weather Topic 3.1.tx", stats4);
        ReadFile.printSentiment("Weather Topic 3.2.txt", stats5);
        ReadFile.printSentiment("Weather Topic 3.3.txt", stats6);


        System.out.println("\nTopic 3: ");
        FileStats stats7 = ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/wine.txt", stopWords, posWords, negWords);
        FileStats stats8 = ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/rats.txt", stopWords, posWords, negWords);
        FileStats stats9 = ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/banana.txt", stopWords, posWords, negWords);
        System.out.println("\n--- Vocabulary Richness ---");
        ReadFile.compareVocabulary("wine.txt", stats7, "wine.txt", stats8);
        ReadFile.compareVocabulary("rats.txt", stats7, "rats.txt", stats9);
        ReadFile.compareVocabulary("banana.txt", stats8, "banana.txt", stats9);
        System.out.println("\n--- Sentiment ---");
        ReadFile.printSentiment("wine.txt", stats7);
        ReadFile.printSentiment("rats", stats8);
        ReadFile.printSentiment("banana.txt", stats9);
    }
}