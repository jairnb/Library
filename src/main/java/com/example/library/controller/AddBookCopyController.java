package com.example.library.controller;

import com.example.library.model.domain.Book;
import com.example.library.services.BookService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookCopyController implements Initializable {
    private Stage dialogStage;
    private Book book;
    private boolean confirmButton = false;
    @FXML private TextField numberOfCopy;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addCopy() {
        BookService.updateCopyNumber(book.getId(), Integer.parseInt(numberOfCopy.getText()));
        confirmButton = false;
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
