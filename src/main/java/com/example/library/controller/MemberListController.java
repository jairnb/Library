package com.example.library.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    @FXML
    private Button addNewButton;

    @FXML
    private Button checkOutEntriesButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableView<?> memberGrid;

    @FXML
    private TableColumn<?, ?> phoneNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onAddNew();
    }

    private void onAddNew(){
        addNewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }
}
