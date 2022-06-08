package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    Stage stage;
    @FXML
    private AnchorPane scenePane;

    public void switchHomepage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchGetStarted(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("getStarted.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader2.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchAboutUs(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("aboutUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader3.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchHelp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("help.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader4.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchRooms(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("rooms.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader5.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTeachers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader6 = new FXMLLoader(HelloApplication.class.getResource("teachers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader6.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchE(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader8 = new FXMLLoader(HelloApplication.class.getResource("events-pref.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader8.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchR(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader9 = new FXMLLoader(HelloApplication.class.getResource("resources-pref.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader9.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void switchSubjects(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader7 = new FXMLLoader(HelloApplication.class.getResource("subjects.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader7.load(), 835, 508);
        stage.setScene(scene);
        stage.show();
    }

    public void setExit() {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}