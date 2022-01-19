package com.example.library.controller;

import com.example.library.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private AnchorPane loginAnchorPane;

    @FXML private TextField idTextField;

    @FXML private Button loginButton;

    @FXML private PasswordField passwordTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/home.fxml"));

        loginAnchorPane.getChildren().setAll(a);
    }
}
