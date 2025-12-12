import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Load dictionary files
        String stopWordsPath = "/Users/ErinDrillock/Programming/Project files/stopwords.txt";
        String positivePath = "/Users/ErinDrillock/Programming/Project files/positive-words.txt";
        String negativePath = "/Users/ErinDrillock/Programming/Project files/negative-words.txt";

        ArrayList<String> stopWords = ReadFile.loadWordList(stopWordsPath);
        ArrayList<String> posWords = ReadFile.loadWordList(positivePath);
        ArrayList<String> negWords = ReadFile.loadWordList(negativePath);

        // Topic library
        ArrayList<String> topic1 = new ArrayList<>();
        topic1.add("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt");
        topic1.add("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt");
        topic1.add("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt");

        ArrayList<String> topic2 = new ArrayList<>();
        topic2.add("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt");
        topic2.add("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt");
        topic2.add("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt");

        ArrayList<String> topic3 = new ArrayList<>();
        topic3.add("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/wine.txt");
        topic3.add("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/rats.txt");
        topic3.add("C://Users//laniy//Downloads//CSPC 2231 Lab 2025/Text Files/banana.txt");

        while (true) {
            System.out.println("\n===============================");
            System.out.println("     TEXT ANALYZER MENU");
            System.out.println("===============================");
            System.out.println("1. Select Topic to Analyze");
            System.out.println("2. Add New Article to Topic");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nChoose topic:");
                    System.out.println("1. Topic 1");
                    System.out.println("2. Topic 2");
                    System.out.println("3. Topic 3");
                    int topicChoice = Integer.parseInt(scanner.nextLine());

                    ArrayList<String> selectedTopic =
                            (topicChoice == 1) ? topic1 :
                                    (topicChoice == 2) ? topic2 :
                                            topic3;

                    runTopicAnalysis(selectedTopic, stopWords, posWords, negWords);
                    break;

                case 2:
                    System.out.println("\nWhich topic do you want to add a file to?");
                    System.out.println("1. Topic 1");
                    System.out.println("2. Topic 2");
                    System.out.println("3. Topic 3");

                    int addChoice = Integer.parseInt(scanner.nextLine());
                    ArrayList<String> targetTopic =
                            (addChoice == 1) ? topic1 :
                                    (addChoice == 2) ? topic2 :
                                            topic3;

                    System.out.print("Enter full file path of the new article: ");
                    String newFile = scanner.nextLine();

                    targetTopic.add(newFile);
                    System.out.println("File added successfully!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void runTopicAnalysis(ArrayList<String> topicFiles,
                                         ArrayList<String> stopWords,
                                         ArrayList<String> posWords,
                                         ArrayList<String> negWords) {

        System.out.println("\nRunning analysis...");

        ArrayList<FileStats> statsList = new ArrayList<>();

        for (String path : topicFiles) {
            statsList.add(ReadFile.readFile(path, stopWords, posWords, negWords));
        }

        System.out.println("\n--- Vocabulary Comparison ---");
        for (int i = 0; i < statsList.size() - 1; i++) {
            ReadFile.compareVocabulary(topicFiles.get(i), statsList.get(i),
                    topicFiles.get(i + 1), statsList.get(i + 1));
        }

        System.out.println("\n--- Sentiment for Each Article ---");
        for (int i = 0; i < statsList.size(); i++) {
            ReadFile.printSentiment(topicFiles.get(i), statsList.get(i));
        }
    }
}
