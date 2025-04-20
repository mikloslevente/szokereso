package com.example.ml_d9zm3p_java;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartWindowController {
@FXML
Button btn_Start;

public void btn_Start_Click(){
    FirstWindowApplication.show();
    Stage stage = (Stage) btn_Start.getScene().getWindow();
    stage.hide();
    }
}
