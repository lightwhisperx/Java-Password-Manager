package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.RegisterMV;
import javafx.stage.Stage;

public class RegisterController
{
    private DBOpsMVController dboMvc;
    private final Stage stage;
    private final RegisterMV registerMV;

    public RegisterController(Stage stage, RegisterMV registerMV) {
        this.stage = stage;
        this.registerMV = registerMV;
        createHandlers();
    }

    private void createHandlers()
    {
        registerMV.getBtnRegister().setOnAction(e -> registerHandler());
        registerMV.getBtnGoLogin().setOnAction(e -> goLoginHandler());
    }

    private void registerHandler()
    {
        dboMvc = new DBOpsMVController();
        if(matchingMasters())
        {
            registerMV.changeSceneToMain(stage);
            dboMvc.getDbOperations().insertIntoMasterTable(dboMvc.getDatabase().getCon(), dboMvc.getHash().toHash(registerMV.getTfConfirmMaster().getText()));
        }
        else
        {
            registerMV.getlMatchingPasswords().setText("Master passwords do not match");
        }
    }

    private void goLoginHandler()
    {
        registerMV.changeSceneToLogin(stage);
    }

    private boolean matchingMasters()
    {
        String masterInput = registerMV.getTfMasterPass().getText();
        String masterConfirm = registerMV.getTfConfirmMaster().getText();

        return masterInput.equals(masterConfirm);
    }
}
