package org.example.network;

import org.example.application.Game;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(7777)) {
            System.out.println("Сервер запщуен");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(server.accept(), 'X'));
                pool.execute(game.new Player(server.accept(), 'O'));
            }
        }
    }
}
