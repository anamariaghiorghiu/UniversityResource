package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SubjectFX {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;
    private final StringProperty tipProperty;

    public SubjectFX() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.tipProperty = new SimpleStringProperty();
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

    public StringProperty getTipProperty() {
        return tipProperty;
    }

    public void setTipProp(String tip) {
        this.tipProperty.set(tip);
    }

}
