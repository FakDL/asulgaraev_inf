import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class SocketClient {
    private Socket client1;
    private Socket client2;

    private PrintWriter toClient1;
    private PrintWriter toClient2; // на EchoServerSocket fromClient
    private BufferedReader fromClient1;
    private BufferedReader fromClient2;// на EchoServerSocket toClient

    public SocketClient(String host, int port) {
        try {
            client1 = new Socket(host, port);
            client2 = new Socket(host, port);

            toClient1 = new PrintWriter(client2.getOutputStream(), true);
            toClient2 = new PrintWriter(client1.getOutputStream(), true);
            fromClient1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            fromClient2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            new Thread(receiverMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toClient1.println(message);
    }

    private Runnable receiverMessagesTask = () -> {
        while (true) {
            try {
                String messageFromClient1 = fromClient1.readLine();
                String messageFromClient2 = fromClient2.readLine();
                if (messageFromClient1 != null) {
                    System.out.println(messageFromClient1);
                }
                if (messageFromClient2 != null){
                    System.out.println(messageFromClient2);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };
}
