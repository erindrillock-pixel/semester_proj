import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile2 {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            try {
                File myObj = new File("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt");
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
                File myObj2 = new File("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.2.txt");
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
                File myObj3 = new File("/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.3.txt");
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