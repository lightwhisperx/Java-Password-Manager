package com.example.cs202igorromanic6138pz;

import com.example.cs202igorromanic6138pz.MVC.MainView.StartMV;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene startScene = new Scene(new StartMV(stage), 600, 450);
        stage.setTitle("I Love MIL(an Tomic)FS");
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}