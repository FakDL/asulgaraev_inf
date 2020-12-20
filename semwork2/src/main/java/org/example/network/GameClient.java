package org.example.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

public class GameClient {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public GameClient() throws IOException {
        socket = new Socket("localhost", 7777);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

}
