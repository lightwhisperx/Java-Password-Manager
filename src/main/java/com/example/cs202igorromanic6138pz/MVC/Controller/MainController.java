package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.MVC.MainView.MainMV;
import com.example.cs202igorromanic6138pz.PwdGenerator.PasswordGenerator;
import com.example.cs202igorromanic6138pz.User.Credentials;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController
{
    private final DBOpsMVController dboMvc = new DBOpsMVController();
    private final Stage stage;
    private final MainMV mainMV;

    public MainController(Stage stage, MainMV mainMV)
    {
        this.stage = stage;
        this.mainMV = mainMV;
        createHandlers();
    }

    private void createHandlers()
    {
        mainMV.getBtnSave().setOnAction(e -> saveHandler());
        mainMV.getBtnDelete().setOnAction(e -> deleteHandler());
        mainMV.getBtnClear().setOnAction(e -> clearHandler());
        mainMV.getBtnRefreshTv().setOnAction(e -> refreshTableHandler());
        mainMV.getBtnLogout().setOnAction(e -> logoutHadnler());
        mainMV.getBtnGenerate().setOnAction(e -> passwordGenerateHandler());
        mainMV.getBtnCopyGenerated().setOnAction(e -> copyGeneratedHandler());
    }

    private void saveHandler()
    {
        Credentials credentials = null;
        if(!mainMV.getTfPlatform().getText().isEmpty() && !mainMV.getTfUsername().getText().isEmpty() && !mainMV.getTfPassword().getText().isEmpty())
        {
            credentials = new Credentials(mainMV.getTfPlatform().getText(), mainMV.getTfUsername().getText(), mainMV.getTfPassword().getText());
            try
            {
                dboMvc.getDbOperations().insertIntoPassDB(dboMvc.getDatabase().getCon(), credentials.getPlatform(), credentials.getUsername(), credentials.getPassword());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            mainMV.getlConfirmation().setText("Successfully saved credentials");
        }
        else if(mainMV.getTfPlatform().getText().isEmpty() || mainMV.getTfUsername().getText().isEmpty() || mainMV.getTfPassword().getText().isEmpty())
        {
            mainMV.getlConfirmation().setText("One or more fields are empty!");
        }
    }

    private void deleteHandler()
    {
        if(mainMV.getTvData().getSelectionModel().getSelectedItem() == null)
        {
            mainMV.getlConfirmation().setText("No password selected!");
        }

        Credentials credentials = mainMV.getTvData().getSelectionModel().getSelectedItem();

        Pattern pattern = Pattern.compile("Platform: (.*?)\\nUsername: (.*?)\\nPassword: (.*)");
        Matcher matcher = pattern.matcher(credentials.toString());

        if (matcher.find()) {
            String platform = matcher.group(1).trim();
            String username = matcher.group(2).trim();

            try
            {
                dboMvc.getDbOperations().deleteFromPassDB(dboMvc.getDatabase().getCon(), credentials.getPlatform(), credentials.getUsername());
                mainMV.getTvData().getItems().remove(credentials);
                mainMV.getlConfirmation().setText("Successfully deleted credentials");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }

    private void clearHandler()
    {
        mainMV.getTfPlatform().clear();
        mainMV.getTfUsername().clear();
        mainMV.getTfPassword().clear();
    }

    private void logoutHadnler()
    {
        mainMV.changeSceneToStart(stage);
    }

    private void refreshTableHandler()
    {
        dboMvc.getDbOperations().fetchData(dboMvc.getDatabase().getCon());
    }

    private void copyGeneratedHandler()
    {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(mainMV.getTfGeneratePassword().getText());
        clipboard.setContent(content);
    }

    private void passwordGenerateHandler()
    {
        PasswordGenerator pwdGen = new PasswordGenerator(8);
        mainMV.getTfGeneratePassword().setText(pwdGen.Generate());
    }
}
