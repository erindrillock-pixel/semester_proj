import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
            readFile("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt");
            readFile("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt");
            readFile("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt");
        }
        public static void readFile(String filePath) {
            try {
                File myFile = new File(filePath);
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while reading: " + filePath);
                e.printStackTrace();
            }
    }
}