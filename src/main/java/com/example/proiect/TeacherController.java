package com.example.proiect;

import Database.TeacherDB;
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

public class TeacherController{
    @FXML
    private TableColumn<Teacher, Integer> colId;
    @FXML
    private TableColumn<Teacher, String> colName;
    @FXML
    private TableColumn<Teacher, String> colGradDidactic;

    Stage stage;
    @FXML
    private TableView teachersTable;
    ActionEvent event;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void initialize() throws Exception{
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colGradDidactic.setCellValueFactory(cellData -> cellData.getValue().getGradDidacticProperty());
        TeacherDB teacher = new TeacherDB();
        ObservableList<Teacher> teacherList = teacher.getAllFields();
        populateTable(teacherList);
    }

    private void populateTable(ObservableList<Teacher> teacherList){
        teachersTable.setItems(teacherList);
    }
}
