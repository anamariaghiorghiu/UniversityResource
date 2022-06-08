package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Room extends Table {
    private final IntegerProperty capacityProperty;

    public Room() {
        this.capacityProperty = new SimpleIntegerProperty();
    }


    public IntegerProperty getCapacityProperty() {
        return capacityProperty;
    }

    public void setCapacityProp(int capacity) {
        this.capacityProperty.set(capacity);
    }


}
