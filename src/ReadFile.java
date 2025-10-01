import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            try {
                File myObj = new File("/Users/ErinDrillock/Programming/Project files/Article 1_ train.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        for (int k = 0; k < 1; k++) {
            try {
                File myObj2 = new File("/Users/ErinDrillock/Programming/Project files/Article 2_ castle.txt");
                Scanner myReader2 = new Scanner(myObj2);
                while (myReader2.hasNextLine()) {
                    String data = myReader2.nextLine();
                    System.out.println(data);
                }
                myReader2.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 1; j++) {
            try {
                File myObj3 = new File("/Users/ErinDrillock/Programming/Project files/Article 3_ roasted meat.txt");
                Scanner myReader3 = new Scanner(myObj3);
                while (myReader3.hasNextLine()) {
                    String data = myReader3.nextLine();
                    System.out.println(data);
                }
                myReader3.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}