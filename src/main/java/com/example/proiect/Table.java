package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Table {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;

    public Table() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
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
}
