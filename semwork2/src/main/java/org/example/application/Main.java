package org.example.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.network.GameClient;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        String fxmlFile = "/fxml/Main.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("11-901");
        stage.setResizable(false);

        stage.setOnCloseRequest(evt -> Platform.exit());

        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch();

        GameClient client = new GameClient();
    }
}
