module com.example.cs202igorromanic6138pz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.cs202igorromanic6138pz to javafx.fxml;
    exports com.example.cs202igorromanic6138pz;
    exports com.example.cs202igorromanic6138pz.UI;
    opens com.example.cs202igorromanic6138pz.UI to javafx.fxml;
    exports com.example.cs202igorromanic6138pz.PwdGenerator;
    opens com.example.cs202igorromanic6138pz.PwdGenerator to javafx.fxml;
}