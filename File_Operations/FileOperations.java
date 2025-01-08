import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileOperations {

    // Write data to a file
    public static void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    // Read data from a file
    public static void readFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // Modify text in a file
    public static void modifyFile(String fileName, String oldText, String newText) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace(oldText, newText));
        }
        Files.write(path, lines);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "sample.txt";
        writeToFile(fileName, "Hello, Codtech IT Solutions!");
        System.out.println("Original Content:");
        readFromFile(fileName);

        modifyFile(fileName, "Codtech", "Codtech Pvt Ltd");
        System.out.println("\nModified Content:");
        readFromFile(fileName);
    }
}
