module com.lab2d.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lab2d.demo to javafx.fxml;
    exports com.lab2d.demo;
}