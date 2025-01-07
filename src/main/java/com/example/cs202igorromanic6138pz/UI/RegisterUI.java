package com.example.cs202igorromanic6138pz.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class RegisterUI extends BorderPane implements UIInterface
{
    private PasswordField tfMasterPass = new PasswordField();
    private PasswordField tfConfirmMaster = new PasswordField();
    private Button btnRegister = new Button("Register");

    public RegisterUI(Stage stage)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(tfMasterPass, tfConfirmMaster, btnRegister);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);

        btnRegister.setOnAction(e -> changeScene(stage));
    }

    @Override
    public void changeScene(Stage stage) {
        //MATCHING MASTERPASS VALIDATION!!!
        stage.setScene(new Scene(new UI(stage), 250, 300));
    }
}
