package org.example.network;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.example.controllers.MainController;

import java.io.PrintWriter;
import java.util.Scanner;

public class ReceiveMessageTask extends Task<Void> {
    private Scanner in;
    private PrintWriter out;
    private MainController controller;

    public ReceiveMessageTask(Scanner in, PrintWriter out, MainController controller) {
        this.in = in;
        this.out = out;
        this.controller = controller;
    }

    @Override
    protected Void call() {
        try {
            String response = in.nextLine();
            char mark = response.charAt(8);
            char opponentMark = mark == 'X' ? 'O' : 'X';
            Platform.runLater(() -> controller.lbl1.setText("Вы играете за " + String.valueOf(mark)));
            while (in.hasNextLine()) {
                System.out.println(response);
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")) {
                    Platform.runLater(() -> controller.lbl1.setText("Ход сделан, ожидайте хода соперника"));
                    Platform.runLater(() -> controller.currentSquare.setText(String.valueOf(mark)));
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    int loc = Integer.parseInt(response.substring(15));
                    Platform.runLater(() -> controller.field[loc].setText(String.valueOf(opponentMark)));
                    Platform.runLater(() -> controller.lbl1.setText("Ваш ход"));
                } else if (response.startsWith("MESSAGE")) {
                    String finalResponse = response;
                    Platform.runLater(() -> controller.lbl1.setText(finalResponse.substring(8)));
                } else if (response.startsWith("VICTORY")) {
                    Platform.runLater(() ->showAlertWithHeaderText("Победа"));
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    Platform.runLater(() ->showAlertWithHeaderText("Поражение"));
                    break;
                } else if (response.startsWith("TIE")) {
                    Platform.runLater(() ->showAlertWithHeaderText("Ничья"));
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    showAlertWithHeaderText("Соперник отключился");
                    break;
                }
            }
            out.println("QUIT");
        } finally {
        }
        return null;
    }
    private void showAlertWithHeaderText(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(message);
        alert.setContentText("");

        alert.showAndWait();
    }
}
