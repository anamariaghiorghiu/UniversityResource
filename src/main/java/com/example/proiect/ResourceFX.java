package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResourceFX {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;
    private final StringProperty nameRoomProperty;
    private final StringProperty nameEventProperty;

    public ResourceFX() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.nameRoomProperty = new SimpleStringProperty();
        this.nameEventProperty = new SimpleStringProperty();
    }

    public IntegerProperty getIdProperty() {
        return idProperty;
    }

    public void setIdProp(int id) {
        this.idProperty.set(id);
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public void setNameProp(String name) {
        this.nameProperty.set(name);
    }

    public StringProperty getNameRoomProperty() {
        return nameRoomProperty;
    }

    public void setNameRoomProp(String nameRoom) {
        this.nameRoomProperty.set(nameRoom);
    }

    public StringProperty getNameEventProperty() {
        return nameEventProperty;
    }

    public void setNameEventProp(String nameEvent) {
        this.nameEventProperty.set(nameEvent);
    }
}
