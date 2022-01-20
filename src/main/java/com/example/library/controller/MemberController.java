package com.example.library.controller;

import com.example.library.model.domain.Address;
import com.example.library.model.domain.Member;
import com.example.library.model.domain.Role;
import com.example.library.model.domain.RoleType;
import com.example.library.services.MemberService;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MemberService memberService = new MemberService();
                try {
                    memberService.addMember(new Member(0,
                            firstName.getText(),
                            lastName.getText(),
                            phoneNumber.getText(),
                            new Address(0,street.getText(),city.getText(),state.getText(),zip.getText()),
                            "",new Role(2, RoleType.MEMEBER.toString())
                            ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
