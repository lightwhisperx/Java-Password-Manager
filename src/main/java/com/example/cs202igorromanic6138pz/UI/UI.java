package com.example.cs202igorromanic6138pz.UI;

import com.example.cs202igorromanic6138pz.PwdGenerator.PasswordGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class UI extends BorderPane
{
    private TextField tfPlatform = new TextField();
    private TextField tfUsername = new TextField();
    private TextField tfPassword = new TextField();
    private TextField tfGeneratePassword = new TextField();

    private Button btnSave = new Button("Save");
    private Button btnDelete = new Button("Delete");
    private Button btnEdit = new Button("Edit");
    private Button btnGenerate = new Button("Generate");
    private Button btnCopyGenerated = new Button("Copy Generated");

    private TableView tvData = new TableView();

    private Label lPasswordStrength = new Label("Password Strength is shown here");

    public UI(Stage stage) {
        VBox vbLeft = new VBox();
        vbLeft.setPadding(new Insets(10));
        vbLeft.getChildren().addAll(tfPlatform, tfUsername, tfPassword, lPasswordStrength, btnSave, btnDelete, btnEdit);
        vbLeft.setSpacing(10);
        vbLeft.setAlignment(Pos.CENTER);
        setLeft(vbLeft);

        tvData.setEditable(false);
        VBox vbCenter = new VBox();
        vbCenter.setPadding(new Insets(10));
        vbCenter.getChildren().addAll(tvData);
        setCenter(vbCenter);

        tfGeneratePassword.setEditable(false);
        VBox vbRight = new VBox();
        vbRight.setPadding(new Insets(10));
        vbRight.getChildren().addAll(tfGeneratePassword, btnGenerate, btnCopyGenerated);
        vbRight.setSpacing(10);
        vbRight.setAlignment(Pos.CENTER);
        setRight(vbRight);

        TableView.TableViewSelectionModel selectionModel = tvData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        btnGenerate.setOnAction(e -> {
            PasswordGenerator pg = new PasswordGenerator(new Random().nextInt(8, 12));
            tfGeneratePassword.setText(pg.Generate());
        });

        btnCopyGenerated.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(tfGeneratePassword.getText());
            clipboard.setContent(content);
        });

        btnSave.setOnAction(e -> {
            //connection fix
            Connection con = null;
            String saveEntry = String.format("INSERT INTO passwords(platform, username, password) VALUES (%s, '%s', '%s')",
                    tfPlatform.getText(), tfUsername.getText(), tfPassword.getText());
            try (Statement stmnt = con.createStatement()){
                ResultSet rs = stmnt.executeQuery(saveEntry);
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        });

        btnDelete.setOnAction(e -> {
           Connection con = null;
           String deleteEntry = String.format("DELETE FROM passwords WHERE platform = '%s' AND username = '%s' AND password = '%s'",
                   tvData.getSelectionModel().getSelectedItems().get(0),tvData.getSelectionModel().getSelectedItems().get(1), tvData.getSelectionModel().getSelectedItems().get(2));

            try(Statement stmnt = con.createStatement()){
                ResultSet rs = stmnt.executeQuery(deleteEntry);
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        });

        btnEdit.setOnAction(e -> {
           Connection con = null;

           tfPlatform.setText(tvData.getSelectionModel().getSelectedItems().get(0).toString());
           tfUsername.setText(tvData.getSelectionModel().getSelectedItems().get(1).toString());
           tfPassword.setText(tvData.getSelectionModel().getSelectedItems().get(2).toString());

           String editEntry = String.format("UPDATE passwords SET platform = '%s', username = '%s', password = '%s' " +
                   "WHERE platform = '%s' AND username = '%s' AND password = '%s'",
                   tvData.getSelectionModel().getSelectedItems().get(0).toString(), tvData.getSelectionModel().getSelectedItems().get(1).toString(),
                   tvData.getSelectionModel().getSelectedItems().get(2).toString(),
                   tfPlatform.getText(), tfUsername.getText(), tfPassword.getText());

           try(Statement stmnt = con.createStatement()){
               ResultSet rs = stmnt.executeQuery(editEntry);
           }
           catch (SQLException ex){
               ex.printStackTrace();
           }

        });
    }
}
