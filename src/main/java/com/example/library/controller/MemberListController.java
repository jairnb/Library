package com.example.library.controller;

import com.example.library.model.domain.Book;
import com.example.library.model.domain.Member;
import com.example.library.services.MemberService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    private ObservableList<Member> memberObservableList;
    private MemberService memberService;

    @FXML
    private Button addNewButton;

    @FXML
    private Button checkOutEntriesButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<Member, Integer> memberId;

    @FXML
    private TableColumn<Member, String> firstName;

    @FXML
    private TableColumn<Member, String> lastName;

    @FXML
    private TableView<Member> memberGrid;

    @FXML
    private TableColumn<Member, String> phoneNumber;

    @FXML
    private TableColumn<Member, Boolean> chkSelect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            init();
            onAddNew();
            onEdit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws Exception {
        memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//        chkSelect.setCellValueFactory(new PropertyValueFactory<>("chkSelect"));

        memberService = new MemberService();
        memberObservableList = FXCollections.observableArrayList(memberService.getAllMembers());
        memberGrid.setItems(memberObservableList);
    }

    private void onEdit() throws  Exception{
        editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openDialog(false);
            }
        });
    }

    private void onAddNew() throws Exception{
        addNewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openDialog(true);
            }
        });
    }


    private void openDialog(boolean isNew){
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(AddBookController.class.getResource("/com/example/library/view/member/addUpdateMember.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();

            dialogStage.setResizable(false);

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            MemberController controller = loader.getController();
            if(isNew){
                dialogStage.setTitle("Add Book");
            }else{
                dialogStage.setTitle("Edit Book");
                TableView.TableViewSelectionModel<Member> selectionModel = memberGrid.getSelectionModel();
                if(selectionModel.getSelectedItem() != null)
                    controller.setMemberId(selectionModel.getSelectedItem().getId());
                else
                    return;
            }

            controller.setDialogStage(dialogStage);
            controller.init();

//            dialogStage.setAlwaysOnTop(true);
            dialogStage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
