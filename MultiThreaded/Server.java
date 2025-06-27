import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8010;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                // accept blocks until a client connects
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted from " 
                    + clientSocket.getRemoteSocketAddress());

                // spawn a thread to handle this client
                new Thread(() -> handleClient(clientSocket))
                    .start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
            PrintWriter toClient = new PrintWriter(
                socket.getOutputStream(), true /* autoFlush */)
        ) {
            toClient.println("hello from the server");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException ignored) { }
        }
    }
}
