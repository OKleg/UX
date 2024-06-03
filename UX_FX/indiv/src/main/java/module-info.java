module com.indiv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.indiv to javafx.fxml;
    exports com.indiv;
}
