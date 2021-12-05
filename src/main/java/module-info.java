module graphics {
    requires javafx.controls;
    requires  javafx.fxml;
    requires java.desktop;

    opens graphics to javafx.graphics, javafx.fxml;

    exports graphics;
}