package com.example.ml_d9zm3p_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class FirstWindowApplication {

    public static void show() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FirstWindowApplication.class.getResource("FirstWindow.fxml"));

        try {
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane, 600, 400);
            stage.setTitle("Szókereső");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

