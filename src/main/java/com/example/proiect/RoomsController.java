package com.example.proiect;

import Database.RoomDB;
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

public class RoomsController {
    @FXML
    private TableColumn<RoomFX, Integer> colId;
    @FXML
    private TableColumn<RoomFX, String> colName;
    @FXML
    private TableColumn<RoomFX, Integer> colCapacity;
    Stage stage;
    @FXML
    private TableView roomsTable;

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
        colCapacity.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty().asObject());
        RoomDB room = new RoomDB();
        ObservableList<RoomFX> roomList = room.getAllFields();
        populateTable(roomList);
    }

    private void populateTable(ObservableList<RoomFX> roomList) {
        roomsTable.setItems(roomList);
    }
}
