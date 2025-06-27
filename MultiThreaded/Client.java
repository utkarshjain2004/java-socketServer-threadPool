import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    try (
                            Socket socket = new Socket(address, port);
                            PrintWriter toSocket = new PrintWriter(
                                    socket.getOutputStream(), true /* autoFlush */);
                            BufferedReader fromSocket = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()))) {
                        // Send
                        toSocket.println("Hello from Client "
                                + socket.getLocalSocketAddress());

                        // Receive
                        String line = fromSocket.readLine();
                        System.out.println("Response from Server -> " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();

        //spawning 100 clients reqs

        for(int i =0;i<100;i++){
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception e) {
                // TODO: handle exception
                return;
            }
        }
    }
}