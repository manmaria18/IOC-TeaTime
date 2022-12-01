module com.example.iocteatime {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.iocteatime to javafx.fxml;
    exports com.example.iocteatime;
}