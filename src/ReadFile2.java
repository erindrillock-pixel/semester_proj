import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile2 {

    public static void main(String[] args) {
        String filePath = "/Users/katherinenemanick/Documents/2025 Fall Courses/CPSC PWL F25/Weather/Weather Topic 3.1.txt"; // Replace with your file path

        try {
            // Read all lines from the text file into a List
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Print out each line
            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}