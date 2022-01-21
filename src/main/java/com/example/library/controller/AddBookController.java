package com.example.library.controller;

import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;
import com.example.library.services.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    private Stage dialogStage;
    private Book book;
    public List<Author> authorList;
    private ObservableList<Author> authorObservableList;
    private boolean confirmButton = false;


    @FXML
    private Button addAuthorButton;

    @FXML
    private TableColumn<Author, String> authorId;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Author, String> credentials;

    @FXML
    private TableColumn<Author, String> firstName;

    @FXML
    private TableView<Author> gridAuthor;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TableColumn<Author, String> lastName;

    @FXML
    private ComboBox<String> maxDayTextField;

    @FXML
    private TableColumn<?, ?> phoneNumber;

    @FXML
    private Button saveId;

    @FXML
    private TextField titleTextField;


    @FXML private AnchorPane bookAnchorPane;
    @FXML private TextField credential;

    private ObservableList<String> unitObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorList = new ArrayList<>();
        init();
    }

//    public void addInDatabase(){
//        if (validateFields(street.getText(), city.getText(),
//                state.getText(), zip.getText(), firstName.getText(),
//                lastName.getText(), phoneNumber.getText(),
//                credential.getText(), shortBio.getText(),
//                isbnTextField.getText(), titleTextField.getText(), maxDayTextField.getValue()
//            )
//        )
//        {
//            Address address  = new Address(street.getText(), city.getText(), state.getText(), zip.getText());
//            Address addressId = AddressDAO.insert(address);
//
//            Author author = new Author(firstName.getText(), lastName.getText(), phoneNumber.getText(), addressId, credential.getText(), shortBio.getText());
//            Author authorResult = AuthorDAO.insert(author, addressId);
//
//            book.setAuthor(authorResult);
//            book.setAvailability("True");
//            book.setIsbn(isbnTextField.getText());
//            book.setTitle(titleTextField.getText());
//            book.setMaxDate(maxDayTextField.getValue());
//            book.setNumberOfCopy(1);
//            book.setNumberAvailable(1);
//            confirmButton = true;
//            dialogStage.close();
//        }
//        else{
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Book Warning");
//            alert.setHeaderText("No empty Field please!");
//            alert.setContentText(null);
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//            stage.setAlwaysOnTop(true);
//            alert.showAndWait();
//        }
//    }

    public void init(){

        addAuthorButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openDialog();
            }
        });

        loadCombo();
        initGrid();
        onSaveClick();
        onCancelClick();
    }

    private void onCancelClick(){
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogStage.close();
            }
        });
    }

    private void onSaveClick(){
        saveId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                String maxDateSelect = maxDayTextField.getSelectionModel().getSelectedItem();

                if(titleTextField.getText().trim().equals("")){
                    alert.setContentText("Title cannot be empty");
                    alert.showAndWait();
                    return;
                }

                if(isbnTextField.getText().trim().equals("")){
                    alert.setContentText("ISBN cannot be empty");
                    alert.showAndWait();
                    return;
                }

                if(maxDateSelect == null){
                    alert.setContentText("Select max days");
                    alert.showAndWait();
                    return;
                }

                if(authorList.size() == 0){
                    alert.setContentText("At least one author");
                    alert.showAndWait();
                    return;
                }

                BookService.addBook(new Book(
                        titleTextField.getText(),
                        isbnTextField.getText(),
                        "",
                        maxDateSelect != null ? maxDateSelect : "",
                        authorList
                ));
                dialogStage.close();
            }
        });
    }

    private void initGrid(){

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        credentials.setCellValueFactory(new PropertyValueFactory<>("credentials"));
        authorId.setCellValueFactory(new PropertyValueFactory<>("authorId"));

        authorObservableList = FXCollections.observableArrayList(authorList);
        gridAuthor.setItems(authorObservableList);
    }

    public void loadCombo(){
        unitObservableList = FXCollections.observableArrayList("21", "7");
        maxDayTextField.setItems(unitObservableList);
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

//    public boolean validateFields(String... fields){
//        for (String f : fields){
//            if (Objects.equals(f, null)){
//                return false;
//            }
//        }
//        return true;
//    }


    private void openDialog(){
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(AuthorController.class.getResource("/com/example/library/view/author/author.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();

            dialogStage.setResizable(false);

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            AuthorController controller = loader.getController();
            dialogStage.setTitle("Add Author");
            controller.setDialogStage(dialogStage);
            controller.init();
            Author author = new Author();
            controller.setAuthor(author);

            dialogStage.showAndWait();
            authorList.add(controller.getAuthor());
            initGrid();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
