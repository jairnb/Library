package com.example.library.controller;

import com.example.library.model.domain.Fine;
import com.example.library.services.FineService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class FineController implements Initializable {

    @FXML
    private TableColumn<Fine, String> datePaymentTC;

    @FXML
    private TableColumn<Fine, String> memberIdTC;

    @FXML
    private TableColumn<Fine, String> memberNameTC;

    @FXML
    private TableView<Fine> paymentTV;

    private ObservableList<Fine> finesObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable(){
        datePaymentTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaymentDate().toString()));
        memberIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckOut().getMember().getUserId()));
        memberNameTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckOut().getMember().getFirstName() + " " + cellData.getValue().getCheckOut().getMember().getLastName()));

        finesObservableList = FXCollections.observableArrayList(FineService.selectAll());
        paymentTV.setItems(finesObservableList);
    }
}
