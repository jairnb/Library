package com.example.library.controller;

import com.example.library.model.dao.AddressDAO;
import com.example.library.model.dao.AuthorDAO;
import com.example.library.model.dao.BookDAO;
import com.example.library.model.domain.Address;
import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {
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
    @FXML private Button addButton;
    @FXML private TableColumn<Book, String> availability;
    @FXML private TableColumn<Book, String> isbn;
    @FXML private TableColumn<Book, String> max_day;
    @FXML private TableColumn<Book, String> title;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addBook() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/example/library/view/book/add.fxml"));
        bookAnchorPane.getChildren().setAll(a);
    }

    public void addInDatabase(){
        Address address  = new Address(street.getText(), city.getText(), state.getText(), zip.getText());
        Address addressId = AddressDAO.insert(address);

        Author author = new Author(firstName.getText(), lastName.getText(), phoneNumber.getText(), addressId, credential.getText(), shortBio.getText());
        int authorId = AuthorDAO.insert(author);

        Book book = new Book(titleTextField.getText(), isbnTextField.getText(), "True", maxDayTextField.getText(), authorId);
        boolean result = BookDAO.insert(book);

//        if (result){
//
//        }
    }
}
