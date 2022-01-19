package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
