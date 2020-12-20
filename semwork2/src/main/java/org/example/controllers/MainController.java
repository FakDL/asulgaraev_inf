package org.example.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.SneakyThrows;
import org.example.network.ReceiveMessageTask;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainController implements Initializable {

    public Button currentSquare = null;
    @FXML
    public Label lbl1;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    public Button[] field = null;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Socket socket;
        Scanner in;
        PrintWriter out;

        socket = new Socket("localhost", 7777);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);


        field = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (int i = 0; i < field.length; i++) {
            final int j = i;
            field[i].setOnAction(e -> {
                currentSquare = field[j];
                out.println("MOVE " + j);
            });
        }


        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(in, out, this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(receiveMessageTask);
    }
}
