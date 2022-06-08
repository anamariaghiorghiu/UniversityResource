package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeacherFX {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private StringProperty gradDidacticProperty;
    private IntegerProperty idMaterie1Property;
    private IntegerProperty idMaterie2Property;
    private IntegerProperty idMaterie3Property;

    public TeacherFX() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.gradDidacticProperty = new SimpleStringProperty();
        this.idMaterie1Property = new SimpleIntegerProperty();
        this.idMaterie2Property = new SimpleIntegerProperty();
        this.idMaterie3Property = new SimpleIntegerProperty();
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

    public StringProperty getGradDidacticProperty() {
        return gradDidacticProperty;
    }

    public void setGradDidacticProp(String grad) {
        this.gradDidacticProperty.set(grad);
    }

    public IntegerProperty getIdMaterie1Property() {
        return idMaterie1Property;
    }

    public void setIdMaterie1Prop(int id1) {
        this.idMaterie1Property.set(id1);
    }


    public IntegerProperty getIdMaterie2Property() {
        return idMaterie2Property;
    }

    public void setIdMaterie2Prop(int id2) {
        this.idMaterie2Property.set(id2);
    }

    public IntegerProperty getIdMaterie3Property() {
        return idMaterie2Property;
    }

    public void setIdMaterie3Prop(int id3) {
        this.idMaterie3Property.set(id3);
    }
}
