package com.example.cs202igorromanic6138pz;

import com.example.cs202igorromanic6138pz.MVC.MainView.StartMV;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PasswordManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene startScene = new Scene(new StartMV(stage), 600, 450);
        stage.setTitle("Password Manager");
        stage.setScene(startScene);
        startScene.setUserAgentStylesheet("styles.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}