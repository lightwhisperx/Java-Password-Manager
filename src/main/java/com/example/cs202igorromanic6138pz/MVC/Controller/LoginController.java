package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.LoginMV;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Types;

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
        boolean isValid = false;

        try
        {
            dboMvc.getDatabase().setSt(dboMvc.getDatabase().getCon().prepareCall("{? = CALL validate_master(?)}"));
            dboMvc.getDatabase().getSt().registerOutParameter(1, Types.BOOLEAN);
            dboMvc.getDatabase().getSt().setString(2, input);

            dboMvc.getDatabase().getSt().execute();
            isValid = dboMvc.getDatabase().getSt().getBoolean(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isValid;
    }
}
