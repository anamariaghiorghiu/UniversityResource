package com.example.proiect;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistic extends Table{
    private final StringProperty assignedRoomProperty;

    public Statistic() {
        this.assignedRoomProperty = new SimpleStringProperty();
    }

    public StringProperty getAssignedRoomProperty() {
        return assignedRoomProperty;
    }
    public void setAssignedRoomProp(String assignedRoom) {
        this.assignedRoomProperty.set(assignedRoom);
    }
}
