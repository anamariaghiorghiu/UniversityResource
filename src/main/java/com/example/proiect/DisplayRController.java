package com.example.proiect;

import Database.ResourceDB;
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

public class DisplayRController {
    @FXML
    private TableColumn<Resource, Integer> colId;
    @FXML
    private TableColumn<Resource, String> colName;
    @FXML
    private TableColumn<Resource, String> colNameRoom;
    @FXML
    private TableColumn<Resource, String> colNameEvent;
    Stage stage;
    @FXML
    private TableView resourcesTable;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resources-pref.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);

        stage.show();
    }
    @FXML
    private void initialize() throws Exception{
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colNameRoom.setCellValueFactory(cellData -> cellData.getValue().getNameRoomProperty());
        colNameEvent.setCellValueFactory(cellData -> cellData.getValue().getNameEventProperty());
        ResourceDB resource = new ResourceDB();
        ObservableList<Resource> resourceList = resource.getAllFields();
        populateTable(resourceList);
    }
    private void populateTable(ObservableList<Resource> resourceList){
        resourcesTable.setItems(resourceList);
    }
}
