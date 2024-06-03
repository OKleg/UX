module com.example.component_exemple {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.component_exemple to javafx.fxml;
    exports com.example.component_exemple;
}