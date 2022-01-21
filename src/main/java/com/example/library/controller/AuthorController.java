package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField city;

    @FXML
    private TextField credentials;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField memberId;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea shortBio;

    @FXML
    private TextField state;

    @FXML
    private TextField street;

    @FXML
    private Label title;

    @FXML
    private TextField zip;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void init(){

    }
}
