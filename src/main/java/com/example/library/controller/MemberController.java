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
    private  Label title;

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

    MemberService memberService;
    Member member;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                memberService = new MemberService();
//                try {
//                    if(memberId.getText().trim().equals("") ){
//                        memberService.addMember(new Member(0,
//                                firstName.getText(),
//                                lastName.getText(),
//                                phoneNumber.getText(),
//                                new Address(0,street.getText(),city.getText(),state.getText(),zip.getText()),
//                                "",new Role(2, RoleType.MEMEBER.toString())
//                        ));
//                    }else{
//                        int id = Integer.parseInt(memberId.getText().trim());
//                        memberService.updateMember(new Member(
//                                id,
//                                firstName.getText(),
//                                lastName.getText(),
//                                phoneNumber.getText(),
//                                new Address(
//                                        member.getAddress().getId(),
//                                        street.getText(),
//                                        city.getText(),
//                                        state.getText(),
//                                        zip.getText()),
//                                "",new Role(2, RoleType.MEMEBER.toString())
//                        ));
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void init() throws Exception {
        memberId.setText("1");

        if(memberId.getText().trim().equals("")){
            title.setText("Add Member");
        }else{
            title.setText("Edit Member");
            memberService = new MemberService();
            int id = Integer.parseInt(memberId.getText().trim());
            member = memberService.getMemberById(id);
            if(member != null){
                firstName.setText(member.getFirstName());
                lastName.setText(member.getLastName());
                phoneNumber.setText(member.getPhoneNumber());
                if(member.getAddress() != null){
                    Address ad = member.getAddress();
                    state.setText(ad.getState());
                    city.setText(ad.getCity());
                    street.setText(ad.getStreet());
                    zip.setText(ad.getPostalCode());
                }
            }
        }
    }

    private void clearTextControl(){

    }
}
