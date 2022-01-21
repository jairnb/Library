package com.example.library.controller;

import com.example.library.model.domain.Address;
import com.example.library.model.domain.Author;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {

    Author author;
    private Stage dialogStage;
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
        init();
    }

    public void init(){
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!validateInput()){
                    return;
                }

                if(author == null)
                    author = new Author();

                author.setFirstName(firstName.getText());
                author.setLastName(lastName.getText());
                author.setPhoneNumber(phoneNumber.getText());
                author.setAddress(new Address(
                        street.getText(),
                        city.getText(),
                        state.getText(),
                        zip.getText()
                ));
                author.setCredentials(credentials.getText());
                author.setShortBio(shortBio.getText());

                dialogStage.close();
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearInput();
            }
        });
    }


    private Boolean validateInput(){
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

        if(this.credentials.getText().trim().equals("")){
            alert.setContentText("Credentials cannot be empty");
            alert.showAndWait();
            return  false;
        }

        return true;
    }

    private void clearInput(){
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        state.setText("");
        city.setText("");
        street.setText("");
        zip.setText("");
        credentials.setText("");
        shortBio.setText("");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public Author getAuthor(){
        return this.author;
    }
}
