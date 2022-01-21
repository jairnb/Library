package com.example.library.controller;

import com.example.library.model.domain.Book;
import com.example.library.services.BookService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddBookCopyController implements Initializable {
    private Stage dialogStage;
    private Book book;
    private boolean confirmButton = false;
    @FXML private TextField numberOfCopy;
    @FXML private Button saveCopyButton;
    @FXML private TextField isbn_number;
    @FXML private Label notFoundLabel;
    @FXML private Label numberOfCopyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void searchByISBN(){
        if (isbn_number.getText() == null || Objects.equals(isbn_number.getText() , "")){
            notFoundLabel.setText("Enter with isbn");
            notFoundLabel.setVisible(true);
            numberOfCopy.setVisible(false);
            saveCopyButton.setVisible(false);
            numberOfCopyLabel.setVisible(false);
        }
        else {
            book = BookService.getByISBN(isbn_number.getText());
            if (book != null){
                numberOfCopy.setVisible(true);
                saveCopyButton.setVisible(true);
                numberOfCopyLabel.setVisible(true);
                notFoundLabel.setVisible(false);
            }
            else {
                notFoundLabel.setText("ISBN not found");
                notFoundLabel.setVisible(true);
                numberOfCopy.setVisible(false);
                saveCopyButton.setVisible(false);
                numberOfCopyLabel.setVisible(false);
            }
        }

    }
    public void addCopy() {
        BookService.updateCopyNumber(book, Integer.parseInt(numberOfCopy.getText()));
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
