module com.example.iocteatime {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.iocteatime to javafx.fxml;
    exports com.example.iocteatime;
    exports com.example.iocteatime.controllersGUI;
    opens com.example.iocteatime.controllersGUI to javafx.fxml;
}