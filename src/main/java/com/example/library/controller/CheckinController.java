package com.example.library.controller;

import com.example.library.model.domain.Checkout;
import com.example.library.model.domain.Member;
import com.example.library.services.CheckinService;
import com.example.library.services.MemberService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CheckinController implements Initializable {
    @FXML
    private TableColumn<Checkout, String> bookTitleTC;
    @FXML
    private TableView<Checkout> checkOutTable;
    @FXML
    private TableColumn<Checkout, String> checkoutDateTC;
    @FXML
    private TableColumn<Checkout, String> dueDateTC;
    @FXML
    private TableColumn<Checkout, String> userIdTC;
    @FXML
    private TableColumn<Checkout, String> userNameTC;
    @FXML
    private TextField txtMemberId;
    private ObservableList<Checkout> checkoutsObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void find() throws Exception {
        Member member = MemberService.getMemberByUserId(txtMemberId.getText());
        if (member != null){
            List<Checkout> memberCheckouts = CheckinService.selectAllUserCheckout(member.getId());
            loadTable(memberCheckouts);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User Warning");
            alert.setHeaderText("Not found");
            alert.setContentText(null);
            alert.showAndWait();
        }

    }

    public void loadTable(List<Checkout> memberCheckouts){
        bookTitleTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getTitle()));
        checkoutDateTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckoutDate().toString()));
        dueDateTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDueDate().toString()));
        userIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getUserId()));
        userNameTC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getFirstName()));

        checkoutsObservableList = FXCollections.observableArrayList(memberCheckouts);
        checkOutTable.setItems(checkoutsObservableList);
    }

    public void checkin(){
        Checkout checkout = checkOutTable.getSelectionModel().getSelectedItem();
        System.out.println(LocalDate.now().compareTo(checkout.getDueDate()));
        if (LocalDate.now().compareTo(checkout.getDueDate()) >= 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Checking Warning");
            alert.setHeaderText("User must pay fine");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Checking Warning");
            alert.setHeaderText("User must pay fine");
            alert.setContentText(null);
            alert.showAndWait();
        }

        CheckinService.updateBookNumberAvailable(checkout.getBook());
    }
}
