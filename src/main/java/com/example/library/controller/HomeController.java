package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button booksButton;

    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private AnchorPane homeMainAnchorPane;

    @FXML
    private Button membersButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void books() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/book/index.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }

    public void members() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/member/memberList.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }

    public void checkout() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/checkout/checkout.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }
}
