package com.example.proiect;

import Database.SubjectDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectsController {
    @FXML
    private TableColumn<SubjectFX, Integer> colId;
    @FXML
    private TableColumn<SubjectFX, String> colName;
    @FXML
    private TableColumn<SubjectFX, String> colTip;
    Stage stage;

    @FXML
    private TableView subjectsTable;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() throws Exception {
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colTip.setCellValueFactory(cellData -> cellData.getValue().getTipProperty());

        SubjectDB subject = new SubjectDB();
        ObservableList<SubjectFX> subjectList = subject.getAllFields();
        populateTable(subjectList);
    }

    private void populateTable(ObservableList<SubjectFX> subjectList) {
        subjectsTable.setItems(subjectList);
    }
}
