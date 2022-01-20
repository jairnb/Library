package com.example.library.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberController extends Application {

    @FXML
    private TextField memberId;

    @FXML
    private TextField city;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField state;

    @FXML
    private TextField street;

    @FXML
    private TextField zip;

    @FXML
    private Button save;

    @Override
    public void start(Stage stage) throws Exception {
        save.setOnAction(e -> {
            Stage g = new Stage();
            g.initModality(Modality.APPLICATION_MODAL);
            g.setTitle("title");

            Label lbl = new Label("hello");
            VBox box = new VBox(10);
            box.getChildren().addAll(lbl);
            box.setAlignment(Pos.CENTER);

            Scene scene = new Scene(box);
            g.setScene(scene);
        });
    }




//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }


}
