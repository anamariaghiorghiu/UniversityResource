package com.example.proiect;

import Database.EventDB;
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

public class DisplayEController {
    @FXML
    private TableColumn<EventFX, Integer> colId;
    @FXML
    private TableColumn<EventFX, String> colName;
    @FXML
    private TableColumn<EventFX, Integer> colCapacity;
    @FXML
    private TableColumn<EventFX, String> colNameTeacher;
    @FXML
    private TableColumn<EventFX, String> colNameSubject;
    Stage stage;
    @FXML
    private TableView eventsTable;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("events-pref.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);

        stage.show();
    }
    @FXML
    private void initialize() throws Exception{
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colCapacity.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty().asObject());
        colNameTeacher.setCellValueFactory(cellData -> cellData.getValue().getNameTeacherProperty());
        colNameSubject.setCellValueFactory(cellData -> cellData.getValue().getNameSubjectProperty());
        EventDB event = new EventDB();
        ObservableList<EventFX> eventList = event.getAllFields();
        populateTable(eventList);
    }
    private void populateTable(ObservableList<EventFX> eventList){
        eventsTable.setItems(eventList);
    }
}
