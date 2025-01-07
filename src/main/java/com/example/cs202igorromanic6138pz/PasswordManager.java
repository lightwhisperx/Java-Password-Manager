package com.example.cs202igorromanic6138pz;

import com.example.cs202igorromanic6138pz.UI.StartUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene startScene = new Scene(new StartUI(stage), 200, 200);
        stage.setTitle("I Love MILFS");
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}