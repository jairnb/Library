package com.example.library.controller;

import com.example.library.UserSingleton;
import com.example.library.model.domain.RoleType;
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
    @FXML
    private Button checkinButton;
    @FXML
    private Button checkoutButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkRole();
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
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/checkbook/checkout.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }

    public void checkin() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/checkbook/checkin.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }

    public void fines() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/fine/index.fxml"));
        contentAnchorPane.getChildren().setAll(a);
    }

    public void checkRole(){
        if (UserSingleton.getUserRole().equals(RoleType.ADMIN.toString())){
            booksButton.setDisable(false);
            membersButton.setDisable(false);
            checkinButton.setDisable(true);
            checkoutButton.setDisable(true);
        }
        if (UserSingleton.getUserRole().equals(RoleType.BOTH.toString())){
            booksButton.setDisable(false);
            membersButton.setDisable(false);
            checkinButton.setDisable(false);
            checkoutButton.setDisable(false);
        }
        if (UserSingleton.getUserRole().equals(RoleType.LIBRARIAN.toString())){
            booksButton.setDisable(true);
            membersButton.setDisable(true);
            checkinButton.setDisable(false);
            checkoutButton.setDisable(false);
        }
    }
}
