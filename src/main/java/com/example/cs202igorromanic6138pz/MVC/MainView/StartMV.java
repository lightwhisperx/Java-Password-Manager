package com.example.cs202igorromanic6138pz.MVC.MainView;

import com.example.cs202igorromanic6138pz.MVC.Controller.SceneChangeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMV extends SceneChangeController
{
    private Button btnLogin = new Button("Login");
    private Button btnRegister = new Button("Register");

    public Button getBtnLogin() {
        return btnLogin;
    }

    public Button getBtnRegister() {
        return btnRegister;
    }

    public StartMV(Stage stage)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(btnLogin, btnRegister);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);

        btnLogin.setOnAction(e -> changeSceneToLogin(stage));
        btnRegister.setOnAction(e -> changeSceneToRegister(stage));
    }
}
