package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomFX {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;
    private final IntegerProperty capacityProperty;

    public RoomFX() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.capacityProperty = new SimpleIntegerProperty();
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

    public IntegerProperty getCapacityProperty() {
        return capacityProperty;
    }

    public void setCapacityProp(int capacity) {
        this.capacityProperty.set(capacity);
    }
}
