package com.example.library.controller;

import com.example.library.model.dao.CheckoutDAO;
import com.example.library.model.domain.Checkout;
import com.example.library.services.CheckoutService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    @FXML private TextField txtISBN;
    @FXML private Label checkoutMsgLabel;
    @FXML private TextField txtMemberId;
    @FXML private TableColumn<Checkout, String> CbookTitle;
    @FXML private TableColumn<Checkout, String> CcheckoutDate;
    @FXML private TableColumn<Checkout, String> CdueDate;
    @FXML private TableColumn<Checkout, String> checkoutMemberName;
    @FXML private TableColumn<Checkout, String> memberId;
    @FXML private TableView<Checkout> checkoutTable;
    private ObservableList<Checkout> checkoutsObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable(){
        CbookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getTitle()));
        CcheckoutDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckoutDate().toString()));
        CdueDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDueDate().toString()));
        memberId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getUserId()));
        checkoutMemberName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getFirstName()));

        checkoutsObservableList = FXCollections.observableArrayList(CheckoutDAO.selectAll());
        checkoutTable.setItems(checkoutsObservableList);
    }

    public void checkout() throws Exception {
        if (!validateEmptyFields()){
            checkoutMsgLabel.setText("Fill the form");
            checkoutMsgLabel.setVisible(true);
        }else {
            String res = CheckoutService.checkoutBook(txtMemberId.getText(), txtISBN.getText());
            checkoutMsgLabel.setText(res);
            checkoutMsgLabel.setVisible(true);
        }
    }

    private boolean validateEmptyFields(){
        if (txtMemberId.getText() == "" || txtISBN.getText() == "") return false;
        return true;
    }
}
