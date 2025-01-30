module com.example.cs202igorromanic6138pz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires org.jsoup;

    opens com.example.cs202igorromanic6138pz to javafx.fxml;
    exports com.example.cs202igorromanic6138pz;
    exports com.example.cs202igorromanic6138pz.PwdGenerator;
    opens com.example.cs202igorromanic6138pz.PwdGenerator to javafx.fxml;
    exports com.example.cs202igorromanic6138pz.PasswordCheck;
    opens com.example.cs202igorromanic6138pz.PasswordCheck to javafx.fxml;
    exports com.example.cs202igorromanic6138pz.User;
    opens com.example.cs202igorromanic6138pz.User to javafx.fxml;
    exports com.example.cs202igorromanic6138pz.MVC.MainView;
    opens com.example.cs202igorromanic6138pz.MVC.MainView to javafx.fxml;
    exports com.example.cs202igorromanic6138pz.MVC.Controller;
    opens com.example.cs202igorromanic6138pz.MVC.Controller to javafx.fxml;
}