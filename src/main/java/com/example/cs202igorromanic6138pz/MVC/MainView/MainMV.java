package com.example.cs202igorromanic6138pz.MVC.MainView;

import com.example.cs202igorromanic6138pz.MVC.Controller.MainController;
import com.example.cs202igorromanic6138pz.MVC.Controller.SceneChangeController;
import com.example.cs202igorromanic6138pz.User.Credentials;
import com.example.cs202igorromanic6138pz.MVC.Controller.DBOpsMVController;
import com.example.cs202igorromanic6138pz.PasswordCheck.PasswordStrengthChecker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMV extends SceneChangeController
{
    private final TextField tfPlatform = new TextField();
    private final TextField tfUsername = new TextField();
    private final TextField tfPassword = new TextField();
    private final TextField tfGeneratePassword = new TextField();

    private final Button btnSave = new Button("Save");
    private final Button btnDelete = new Button("Delete");
    private final Button btnGenerate = new Button("Generate");
    private final Button btnCopyGenerated = new Button("Copy Generated");
    private final Button btnLogout = new Button("Logout");
    private final Button btnClear = new Button("Clear");
    private final Button btnRefreshTv = new Button("Refresh Table");

    private final Label lConfirmation = new Label("Confirmation");
    private TableView<Credentials> tvData = new TableView<>();
    private TableColumn<Credentials, String> tvPlatformCol = new TableColumn<>("Platform");
    private TableColumn<Credentials, String> tvUsernameCol = new TableColumn<>("Username");
    private TableColumn<Credentials, String> tvPasswordCol = new TableColumn<>("Password");

    private final Label lPasswordStrength = new Label("Password Strength is shown here");

    private final DBOpsMVController dboMvc = new DBOpsMVController();

    public TextField getTfPlatform() {
        return tfPlatform;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnGenerate() {
        return btnGenerate;
    }

    public Button getBtnCopyGenerated() {
        return btnCopyGenerated;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public Button getBtnClear() {
        return btnClear;
    }

    public Button getBtnRefreshTv() {
        return btnRefreshTv;
    }

    public Label getlPasswordStrength() {
        return lPasswordStrength;
    }

    public TextField getTfGeneratePassword() {
        return tfGeneratePassword;
    }

    public Label getlConfirmation() {
        return lConfirmation;
    }

    public TableView<Credentials> getTvData() {
        return tvData;
    }

    public MainMV(Stage stage) {
        loadUI();

        new MainController(stage, this);
    }

    private void loadUI()
    {
        VBox vbLeft = new VBox();
        vbLeft.setPadding(new Insets(10));
        vbLeft.getChildren().addAll(tfPlatform, tfUsername, tfPassword, lPasswordStrength, btnSave, btnDelete, btnClear);
        vbLeft.setSpacing(10);
        vbLeft.setAlignment(Pos.CENTER);
        setLeft(vbLeft);

        tvData.setEditable(false);
        setTvData();
        VBox vbCenter = new VBox();
        vbCenter.setPadding(new Insets(10));
        vbCenter.getChildren().addAll(lConfirmation, tvData, btnRefreshTv, btnLogout);
        vbCenter.setSpacing(10);
        vbCenter.setAlignment(Pos.CENTER);
        setCenter(vbCenter);


        tfGeneratePassword.setEditable(false);
        VBox vbRight = new VBox();
        vbRight.setPadding(new Insets(10));
        vbRight.getChildren().addAll(tfGeneratePassword, btnGenerate, btnCopyGenerated);
        vbRight.setSpacing(10);
        vbRight.setAlignment(Pos.CENTER);
        setRight(vbRight);

        tfPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            PasswordStrengthChecker psc = new PasswordStrengthChecker(newValue);
            lPasswordStrength.setText(psc.checkStrength(newValue));
        });
    }

    private void setTvData()
    {
        TableView.TableViewSelectionModel selectionModel = tvData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        tvPlatformCol.setCellValueFactory(new PropertyValueFactory<>("Platform"));
        tvUsernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tvPasswordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));

        tvData.getColumns().setAll(tvPlatformCol, tvUsernameCol, tvPasswordCol);
        tvData.setItems(dboMvc.getDbOperations().fetchData(dboMvc.getDatabase().getCon()));
    }
}
