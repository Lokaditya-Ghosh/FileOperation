import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started...");

        List<Socket> clients = new ArrayList<>();
        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            new Thread(new ClientHandler(clientSocket, clients)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private List<Socket> clients;

    public ClientHandler(Socket socket, List<Socket> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while ((message = reader.readLine()) != null) {
                for (Socket client : clients) {
                    if (client != socket) {
                        new PrintWriter(client.getOutputStream(), true).println(message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
