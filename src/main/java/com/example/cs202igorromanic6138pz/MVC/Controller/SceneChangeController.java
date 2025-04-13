package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.LoginMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.MainMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.RegisterMV;
import com.example.cs202igorromanic6138pz.MVC.MainView.StartMV;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class SceneChangeController extends BorderPane implements SceneChangeInterface
{
    @Override
    public void changeSceneToLogin(Stage stage)
    {
        Scene scene = new Scene(new LoginMV(stage), 600, 450);
        stage.setScene(scene);
        scene.setUserAgentStylesheet("styles.css");
    }

    @Override
    public void changeSceneToRegister(Stage stage) {
        Scene scene = new Scene(new RegisterMV(stage), 600, 450);
        stage.setScene(scene);
        scene.setUserAgentStylesheet("styles.css");
    }

    @Override
    public void changeSceneToMain(Stage stage) {
        Scene scene = new Scene(new MainMV(stage), 750, 600);
        stage.setScene(scene);
        scene.setUserAgentStylesheet("styles.css");
    }

    @Override
    public void changeSceneToStart(Stage stage){
        Scene scene = new Scene(new StartMV(stage), 600, 450);
        stage.setScene(scene);
        scene.setUserAgentStylesheet("styles.css");
    }
}
