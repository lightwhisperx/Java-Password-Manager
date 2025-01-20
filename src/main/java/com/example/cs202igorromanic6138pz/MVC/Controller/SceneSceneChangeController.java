package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.LoginMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.MainMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.RegisterMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.StartMV;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class SceneSceneChangeController extends BorderPane implements SceneChangeController
{
    @Override
    public void changeSceneToLogin(Stage stage)
    {
        stage.setScene(new Scene(new LoginMV(stage), 600, 450));
    }

    @Override
    public void changeSceneToRegister(Stage stage) {
        stage.setScene(new Scene(new RegisterMV(stage), 600, 450));
    }

    @Override
    public void changeSceneToMain(Stage stage) {
        stage.setScene(new Scene(new MainMV(stage), 600, 450));
    }

    @Override
    public void changeSceneToStart(Stage stage){
        stage.setScene(new Scene(new StartMV(stage), 600, 450));
    }
}
