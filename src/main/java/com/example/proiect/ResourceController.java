package com.example.proiect;

import Database.AssignResource;
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

public class ResourceController {
    @FXML
    private Text booleanVal;
    @FXML
    private Button addResource;
    Stage stage;
    @FXML
    private Text resource;
    @FXML
    private Text numberOfResources;
    @FXML
    private TextField nameResource;
    @FXML
    private TextField roomResource;
    @FXML
    private TextField eventResource;

    String nameResourceString;
    String roomResourceString;
    String eventResourceString;

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getStarted.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void submit() throws SQLException {
        nameResourceString = nameResource.getText();
        roomResourceString = roomResource.getText();
        eventResourceString = eventResource.getText();
        if (Objects.equals(nameResourceString, "") || Objects.equals(roomResourceString, "") ||
                Objects.equals(eventResourceString, "")) {
            booleanVal.setText("some fields are empty!");
            addResource.setDisable(true);
            if (Objects.equals(nameResourceString, "")) {
                addResource.setDisable(false);
                booleanVal.setText("try again!");
            } else if (Objects.equals(roomResourceString, "")) {
                addResource.setDisable(false);
                booleanVal.setText("try again!");
            } else if (Objects.equals(eventResourceString, "")) {
                addResource.setDisable(false);
                booleanVal.setText("try again!");
            }
        } else {
            AssignResource assignResource = new AssignResource();
            Integer number;
            resource.setText(nameResourceString);
            booleanVal.setText("resource added successfully!");
            addResource.setDisable(true);
            assignResource.add(nameResourceString, roomResourceString, eventResourceString);
            number = assignResource.getResourceNumber(nameResourceString);
            numberOfResources.setText(String.valueOf(number));
            if (number == 0) {
                booleanVal.setText("not enough resourses!");
            }
        }
    }

    public void switchResources(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resources.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }
}
