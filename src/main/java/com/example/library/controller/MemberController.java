package com.example.library.controller;

import com.example.library.model.domain.*;
import com.example.library.services.MemberService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;


public class MemberController implements Initializable {
    private Stage dialogStage;
    private Member member;

    MemberService memberService;


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
    private ComboBox<String> role;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField confirmPassword;

    @FXML
    public TextField userId;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;


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
                memberService = new MemberService();
                try {
                    String roleSelect = role.getSelectionModel().getSelectedItem();

                    if(memberId.getText().trim().equals("") ){
                        if(!validateInput()){
                            return;
                        }

                        if(!validateUser()){
                            return;
                        }

                        memberService.addMember(new Member(0,
                                firstName.getText(),
                                lastName.getText(),
                                phoneNumber.getText(),
                                new Address(0,street.getText(),city.getText(),state.getText(),zip.getText()),
                                passwordField.getText(),
                                roleSelect.equals("[select]") ? "" : roleSelect,
                                userId.getText()
                        ));
                    }else{
                        int id = Integer.parseInt(memberId.getText().trim());
                        memberService.updateMember(new Member(
                                id,
                                firstName.getText(),
                                lastName.getText(),
                                phoneNumber.getText(),
                                new Address(
                                        member.getAddress().getId(),
                                        street.getText(),
                                        city.getText(),
                                        state.getText(),
                                        zip.getText()),
                                passwordField.getText(),
                                roleSelect.equals("[select]") ? "" : roleSelect,
                                userId.getText()
                        ));
                    }
                    dialogStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearTextControl();
                dialogStage.close();
            }
        });
    }

    public void init() throws Exception {
        String roles[] =
                { "[select]",RoleType.ADMIN.name() , RoleType.LIBRARIAN.name(), RoleType.BOTH.name()};

        role.setItems(FXCollections.observableArrayList(roles));

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
                passwordField.setText(member.getPassword());
                role.getSelectionModel().select(member.getRole());
                userId.setText(member.getUserId());

            }
        }
    }

    private void clearTextControl(){
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        state.setText("");
        city.setText("");
        street.setText("");
        zip.setText("");
    }

    private boolean validateInput(){
        Alert alert = new Alert(Alert.AlertType.WARNING);

        if(this.firstName.getText().trim().equals("")){
            alert.setContentText("First name cannot be empty");
            alert.showAndWait();
            return  false;
        }

        if(this.lastName.getText().trim().equals("")){
            alert.setContentText("Last name cannot be empty");
            alert.showAndWait();
            return  false;
        }

        if(this.phoneNumber.getText().trim().equals("")){
            alert.setContentText("Phone number cannot be empty");
            alert.showAndWait();
            return  false;
        }


        return  true;
    }

    private Boolean validateUser() throws Exception {
        String roleSelect = role.getSelectionModel().getSelectedItem();
        memberService = new MemberService();

        if(!(roleSelect.equals(RoleType.ADMIN) || roleSelect.equals(RoleType.LIBRARIAN))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if(userId.getText().equals("")){
                alert.setContentText("User id is required");
                alert.showAndWait();
                return false;
            }

            if(memberService.isUserIdExisted(userId.getText())){
                alert.setContentText("User id is already existed");
                alert.showAndWait();
                return false;
            }

            if(passwordField.getText().equals("")){
                alert.setContentText("Password is required");
                alert.showAndWait();
                return false;
            }

            if(!passwordField.getText().equals(confirmPassword.getText())){
                alert.setContentText("Password are not match");
                alert.showAndWait();
                return false;
            }
        }
        return  true;
    }

    public void setDialogStage(Stage dialogStage) {
        System.out.println("setDialog");
        this.dialogStage = dialogStage;
    }

//    public Member getBook() {
//        return member;
//    }

//    public void setBook(Member book) {
//        this.member = member;
//    }

    public void setMemberId(int id) {
        System.out.println("setMemberId");
        memberId.setText(id + "");
    }

}
