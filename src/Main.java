import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        ArrayList<String> stopWords = ReadFile.loadStopWords(stopWordsPath);

        System.out.println("\nTopic 1: ");
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt", stopWords);
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt", stopWords);
        ReadFile.readFile("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt", stopWords);

        System.out.println("\nTopic 2: ");
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt", stopWords);
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt", stopWords);
        ReadFile.readFile("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt", stopWords);

        System.out.println("\nTopic 3: ");
        ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/wine.txt", stopWords);
        ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/rats.txt", stopWords);
        ReadFile.readFile("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/banana.txt", stopWords);
    }
}