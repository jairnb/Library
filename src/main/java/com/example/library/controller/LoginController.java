package com.example.library.controller;

import com.example.library.UserSingleton;
import com.example.library.services.MemberService;
import com.example.library.utils.PassEncTech2;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    MemberService memberService;

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login() throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if(idTextField.getText().equals("")){
            alert.setContentText("User id is required");
            alert.showAndWait();
            return;
        }

        if(passwordTextField.getText().equals("")){
            alert.setContentText("Password id is required");
            alert.showAndWait();
            return;
        }

        memberService = new MemberService();

//        String hashPwd = PassEncTech2.toHexString( PassEncTech2.getSHA(passwordTextField.getText()));
//        System.out.println(hashPwd);
        if(memberService.isUserPasswordCorrect(idTextField.getText(), passwordTextField.getText())){
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/home.fxml"));
            loginAnchorPane.getChildren().setAll(a);
        }else{
            alert.setContentText("User id or password is incorrect!");
            alert.showAndWait();
            return;
        }

    }
}
