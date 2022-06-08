package com.example.proiect;

import Database.AssignEvent;
import Database.EventDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EventController {
    Stage stage;
    @FXML
    private Text booleanVal;
    @FXML
    private Button addEvent;
    @FXML
    private TextField nameEvent;
    @FXML
    private TextField capacityEvent;
    @FXML
    private TextField nameTeacher;
    @FXML
    private TextField nameSubject;

    String nameEventString;
    String capacityEventString;
    String nameTeacherString;
    String nameSubjectString;


    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getStarted.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void submit() throws SQLException {
        nameEventString = nameEvent.getText();
        capacityEventString = capacityEvent.getText();
        nameTeacherString = nameTeacher.getText();
        nameSubjectString = nameSubject.getText();

        if (Objects.equals(nameEventString, "") || Objects.equals(capacityEventString, "") ||
                Objects.equals(nameTeacherString, "") || Objects.equals(nameSubjectString, "")) {
            booleanVal.setText("some fields are empty!");
            addEvent.setDisable(true);
            if (Objects.equals(nameEventString, "")) {
                addEvent.setDisable(false);
                booleanVal.setText("try again!");
            } else if (Objects.equals(capacityEventString, "")) {
                addEvent.setDisable(false);
                booleanVal.setText("try again!");
            } else if (Objects.equals(nameTeacherString, "")) {
                addEvent.setDisable(false);
                booleanVal.setText("try again!");
            } else if (Objects.equals(nameSubjectString, "")) {
                addEvent.setDisable(false);
                booleanVal.setText("try again!");
            }
        } else {
            EventDB event = new EventDB();
            AssignEvent assignEvent = new AssignEvent();
            booleanVal.setText("event added successfully!");
            addEvent.setDisable(true);
            event.add(nameEventString, Integer.parseInt(capacityEventString),
                    nameTeacherString, nameSubjectString);
            assignEvent.assignEvent();
            assignEvent.readData("SALA_ASIGNATA");

        }
    }

    public void switchEvents(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("events.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchStatistics(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("stats.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader2.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }
}
