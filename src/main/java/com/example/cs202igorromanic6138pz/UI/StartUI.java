package com.example.cs202igorromanic6138pz.UI;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartUI extends BorderPane
{
    private Button btnLogin = new Button("Login");
    private Button btnRegister = new Button("Register");

    public StartUI(Stage stage)
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

    private void changeSceneToRegister(Stage stage)
    {
        stage.setScene(new Scene(new RegisterUI(stage)));
    }

    private void changeSceneToLogin(Stage stage)
    {
        stage.setScene(new Scene(new LoginUI(stage)));
    }
}
