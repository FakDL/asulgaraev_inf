package org.example.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    int [] field = new int[9];

    Player currentPlayer;

    public boolean hasWinner() {
        return (field[0] != 0 && field[0] == field[1] && field[0] == field[2])
                || (field[3] != 0 && field[3] == field[4] && field[3] == field[5])
                || (field[6] != 0 && field[6] == field[7] && field[6] == field[8])
                || (field[0] != 0 && field[0] == field[3] && field[0] == field[6])
                || (field[1] != 0 && field[1] == field[4] && field[1] == field[7])
                || (field[2] != 0 && field[2] == field[5] && field[2] == field[8])
                || (field[0] != 0 && field[0] == field[4] && field[0] == field[8])
                || (field[2] != 0 && field[2] == field[4] && field[2] == field[6]);
    }

    public boolean boardFilledUp() {
        return Arrays.stream(field).allMatch(p -> p != 0);
    }

    public synchronized void move(int location, Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("Не ваш ход");
        } else if (player.opponent == null) {
            throw new IllegalStateException("Ожидайте пока соперник присоединится");
        } else if (field[location] != 0) {
            throw new IllegalStateException("Занятое поле");
        }
        field[location] = currentPlayer.mark;
        currentPlayer = currentPlayer.opponent;
    }

    public class Player implements Runnable {
        char mark;
        Player opponent;
        Socket socket;
        Scanner input;
        PrintWriter output;

        public Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
        }

        @Override
        public void run() {
            try {
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + mark);
            if (mark == 'X') {
                currentPlayer = this;
                output.println("MESSAGE Ожидайте соперника");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                opponent.output.println("MESSAGE Ваш ход");
            }
        }

        private void processCommands() {
            while (input.hasNextLine()) {
                String command = input.nextLine();
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    processMoveCommand(Integer.parseInt(command.substring(5)));
                }
            }
        }

        private void processMoveCommand(int location) {
            try {
                move(location, this);
                output.println("VALID_MOVE");
                opponent.output.println("OPPONENT_MOVED " + location);
                if (hasWinner()) {
                    output.println("VICTORY");
                    opponent.output.println("DEFEAT");
                } else if (boardFilledUp()) {
                    output.println("TIE");
                    opponent.output.println("TIE");
                }
            } catch (IllegalStateException e) {
                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}
