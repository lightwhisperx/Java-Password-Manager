package com.example.cs202igorromanic6138pz.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.stage.Stage;


public class LoginUI extends BorderPane implements UIInterface
{
    private PasswordField tfMasterPass = new PasswordField();
    private Button btnLogin = new Button("Login");

    public LoginUI(Stage stage)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(tfMasterPass, btnLogin);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);

        btnLogin.setOnAction(e -> changeScene(stage));
    }

    @Override
    public void changeScene(Stage stage) {
        //MASTERPASS VALIDATION!!!!
        stage.setScene(new Scene(new UI(stage), 250, 300));
    }
}
