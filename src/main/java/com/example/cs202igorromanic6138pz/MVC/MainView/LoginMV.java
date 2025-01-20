package com.example.cs202igorromanic6138pz.MVC.MainView;

import com.example.cs202igorromanic6138pz.MVC.Controller.DBOpsMVController;
import com.example.cs202igorromanic6138pz.MVC.Controller.LoginController;
import com.example.cs202igorromanic6138pz.MVC.Controller.SceneSceneChangeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class LoginMV extends SceneSceneChangeController
{
    private final PasswordField tfMasterPass = new PasswordField();
    private final Button btnLogin = new Button("Login");
    private final Label lInvalid = new Label("");
    private final Separator sep = new Separator();
    private final Label lNoMaster = new Label("If you don't have a master password you can register one :)");
    private final Button btnGoRegister = new Button("Go Register");

    public PasswordField getTfMasterPass() {
        return tfMasterPass;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public Button getBtnGoRegister() {
        return btnGoRegister;
    }

    public Label getlInvalid() {
        return lInvalid;
    }

    public LoginMV(Stage stage)
    {
        loadUI();

        new LoginController(stage, this);
    }

    private void loadUI()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(tfMasterPass, lInvalid, btnLogin, sep, lNoMaster, btnGoRegister);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);
    }
}
