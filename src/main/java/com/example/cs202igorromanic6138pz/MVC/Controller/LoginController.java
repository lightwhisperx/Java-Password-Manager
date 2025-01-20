package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.LoginMV;
import javafx.stage.Stage;

public class LoginController
{
    private final DBOpsMVController dboMvc = new DBOpsMVController();
    private final Stage stage;
    private final LoginMV loginMV;

    public LoginController(Stage stage, LoginMV loginMV) {
        this.stage = stage;
        this.loginMV = loginMV;
        createHandlers();
    }

    private void createHandlers()
    {
        loginMV.getBtnLogin().setOnAction(e -> loginHandler());
        loginMV.getBtnGoRegister().setOnAction(e -> goRegisterHandler());
    }

    private void loginHandler()
    {
        if(validMaster())
        {
            loginMV.changeSceneToMain(stage);
        }
        else
        {
            loginMV.getlInvalid().setText("Invalid master password");
        }
    }

    private void goRegisterHandler()
    {
        loginMV.changeSceneToRegister(stage);
    }

    private boolean validMaster()
    {
        String input = dboMvc.getHash().toHash(loginMV.getTfMasterPass().getText());
        String master = null;

        try
        {
            master = dboMvc.getDbOperations().selectFromMaster(dboMvc.getDatabase().getCon());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return input.equals(master);
    }
}
