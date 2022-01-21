package com.example.library.controller;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.domain.Book;
import com.example.library.services.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> availability;
    @FXML private TableColumn<Book, Integer> numberOfCopy;
    @FXML private TableColumn<Book, String> isbn;
    @FXML private TableColumn<Book, String> max_day;
    @FXML private TableColumn<Book, String> title;
    private ObservableList<Book> bookObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable(){
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        availability.setCellValueFactory(new PropertyValueFactory<>("numberAvailable"));
        numberOfCopy.setCellValueFactory(new PropertyValueFactory<>("numberOfCopy"));
        max_day.setCellValueFactory(new PropertyValueFactory<>("maxDate"));

        bookObservableList = FXCollections.observableArrayList(BookDAO.selectAll());
        bookTable.setItems(bookObservableList);
    }

    public void addBook() throws IOException {
        Book book = new Book();
        boolean btn = addBookForm(book);
        if(btn){
            BookService.addBook(book);
            loadTable();
        }
    }


    public void addCopy() throws IOException {
        Book book = bookTable.getSelectionModel().getSelectedItem();

//        if (book == null){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Book Warning");
//            alert.setHeaderText("Select one book in table first");
//            alert.setContentText(null);
//            alert.showAndWait();
//        }else {
            addCopyForm(book);
            loadTable();
//        }
    }

    public boolean addBookForm(Book book) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(AddBookController.class.getResource("/com/example/library/view/book/add.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Book");
        dialogStage.setResizable(false);

        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        AddBookController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBook(book);


//        dialogStage.setAlwaysOnTop(true);
        dialogStage.showAndWait();
        return controller.isConfirm();
    }

    public void addCopyForm(Book book) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(AddBookCopyController.class.getResource("/com/example/library/view/book/addCopy.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Book Copy");
        dialogStage.setResizable(false);

        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        AddBookCopyController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBook(book);

        dialogStage.setAlwaysOnTop(true);
        dialogStage.showAndWait();
    }
}
