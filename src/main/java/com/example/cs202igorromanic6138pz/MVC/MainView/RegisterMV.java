package com.example.cs202igorromanic6138pz.MVC.MainView;

import com.example.cs202igorromanic6138pz.MVC.Controller.RegisterController;
import com.example.cs202igorromanic6138pz.MVC.Controller.SceneChangeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class RegisterMV extends SceneChangeController
{
    private final PasswordField tfMasterPass = new PasswordField();
    private final PasswordField tfConfirmMaster = new PasswordField();
    private final Button btnRegister = new Button("Register");
    private final Label lMatchingPasswords = new Label("");
    private final Separator sep = new Separator();
    private final Label lGoLogin = new Label("If you already have a master password, you can just log in :)");
    private final Button btnGoLogin = new Button("Go Login");


    public PasswordField getTfMasterPass() {
        return tfMasterPass;
    }

    public PasswordField getTfConfirmMaster() {
        return tfConfirmMaster;
    }

    public Button getBtnRegister() {
        return btnRegister;
    }

    public Label getlMatchingPasswords() {
        return lMatchingPasswords;
    }

    public Button getBtnGoLogin() {
        return btnGoLogin;
    }


    public RegisterMV(Stage stage)
    {
        loadUI();

        new RegisterController(stage, this);
    }

    private void loadUI()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(tfMasterPass, tfConfirmMaster, lMatchingPasswords, btnRegister, sep, lGoLogin, btnGoLogin);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);
    }
}
