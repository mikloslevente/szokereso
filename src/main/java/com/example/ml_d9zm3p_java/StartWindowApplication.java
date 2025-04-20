package com.example.ml_d9zm3p_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StartWindowApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindowApplication.class.getResource("StartWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 645, 342);
        stage.setTitle("Szókereső");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}