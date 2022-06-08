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

public class TeachersController {
    @FXML
    private TableColumn<TeacherFX, Integer> colId;
    @FXML
    private TableColumn<TeacherFX, String> colName;
    @FXML
    private TableColumn<TeacherFX, String> colGradDidactic;
    @FXML
    private TableColumn<TeacherFX, Integer> colIdMaterie1;
    @FXML
    private TableColumn<TeacherFX, Integer> colIdMaterie2;
    @FXML
    private TableColumn<TeacherFX, Integer> colIdMaterie3;
    Stage stage;
    @FXML
    private TableView teachersTable;

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
        colIdMaterie1.setCellValueFactory(cellData -> cellData.getValue().getIdMaterie1Property().asObject());
        colIdMaterie2.setCellValueFactory(cellData -> cellData.getValue().getIdMaterie2Property().asObject());
        colIdMaterie3.setCellValueFactory(cellData -> cellData.getValue().getIdMaterie3Property().asObject());
        TeacherDB teacher = new TeacherDB();
        ObservableList<TeacherFX> teacherList = teacher.getAllFields();
        populateTable(teacherList);
    }

    private void populateTable(ObservableList<TeacherFX> teacherList){
        teachersTable.setItems(teacherList);
    }
}
