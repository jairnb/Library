package com.example.library.controller;

import com.example.library.model.dao.AddressDAO;
import com.example.library.model.dao.AuthorDAO;
import com.example.library.model.domain.Address;
import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    private Stage dialogStage;
    private Book book;
    private boolean confirmButton = false;
    @FXML private AnchorPane bookAnchorPane;
    @FXML private TextField city;
    @FXML private TextField credential;
    @FXML private TextField maxDayTextField;
    @FXML private TextField firstName;
    @FXML private TextField isbnTextField;
    @FXML private TextField lastName;
    @FXML private TextField phoneNumber;
    @FXML private TextField shortBio;
    @FXML private TextField state;
    @FXML private TextField street;
    @FXML private TextField titleTextField;
    @FXML private TextField zip;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addInDatabase(){
        Address address  = new Address(street.getText(), city.getText(), state.getText(), zip.getText());
        Address addressId = AddressDAO.insert(address);

        Author author = new Author(firstName.getText(), lastName.getText(), phoneNumber.getText(), addressId, credential.getText(), shortBio.getText());
        Author authorResult = AuthorDAO.insert(author, addressId);

        book.setAuthor(authorResult);
        book.setAvailability("True");
        book.setIsbn(isbnTextField.getText());
        book.setTitle(titleTextField.getText());
        book.setMaxDate(maxDayTextField.getText());
        confirmButton = true;
        dialogStage.close();
    }

    public boolean isConfirm(){
        return confirmButton;
    }
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
