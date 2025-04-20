module com.example.ml_d9zm3p_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.ml_d9zm3p_java to javafx.fxml;
    exports com.example.ml_d9zm3p_java;
}