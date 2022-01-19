package com.example.library.controller;

import com.example.library.Main;
import com.example.library.model.database.Database;
import com.example.library.model.database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private AnchorPane loginAnchorPane;

    @FXML private TextField idMemberTextField;

    @FXML private Button loginButton;

    @FXML private TextField passwordTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
