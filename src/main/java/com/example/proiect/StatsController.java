package com.example.proiect;

import Database.EventDB;
import Database.StatsDB;
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

public class StatsController {
    @FXML
    private TableColumn<StatsFX, Integer> colId;
    @FXML
    private TableColumn<StatsFX, String> colName;
    @FXML
    private TableColumn<StatsFX, String> colAssignedRoom;
    Stage stage;
    @FXML
    private TableView statsTable;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("events-pref.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() throws Exception {
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colAssignedRoom.setCellValueFactory(cellData -> cellData.getValue().getAssignedRoomProperty());
        StatsDB stats = new StatsDB();
        ObservableList<StatsFX> statsList = stats.getAllFields();
        populateTable(statsList);
    }

    private void populateTable(ObservableList<StatsFX> statsList) {
        statsTable.setItems(statsList);
    }
}
