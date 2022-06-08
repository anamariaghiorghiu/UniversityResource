package com.example.proiect;

import Database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static void main(String[] args) throws SQLException, IOException {
        launch();
        RoomDB room = new RoomDB();
        room.deleteAll();
        room.readFromWebsite();
        room.generateRoomCapacity();

        TeacherDB teacher = new TeacherDB();
        teacher.generateSubjectId();
        room.dropView();
        room.generateRoomCapacity();
        room.orderByCapacity();

        ResourceDB resource = new ResourceDB();
        resource.deleteAll();
        resource.add("videoproiector", 7);
        resource.add("laptop", 1);

        EventDB event = new EventDB();
        event.deleteAll();
        event.add("Curs QC", 100, "Andreea Arusoaie", "Quantum Computing");
        event.add("Curs Mate", 200, "Andreea Arusoaie", "Mate");
        event.add("Curs Java", 220, "Cristian Frasinaru", "Java");
        event.add("Curs Jva", 50, "Cristian Frasinaru", "Jva");

        AssignEvent eventAssignation = new AssignEvent();
        eventAssignation.assignEvent();

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 508);
        Image icon = new Image("F:\\Totul\\1.Facultate\\ANUL 2\\Semestrul 2\\PA(java)\\Proiect\\src" +
                "\\main\\resources\\photos\\calendar-icon.jpg");
        stage.getIcons().add(icon);
        stage.setTitle("University Resources");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}