import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to server...");

        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            writer.println(scanner.nextLine());
        }
    }
}
