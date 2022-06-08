package com.example.proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventFX {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;

    private final IntegerProperty capacityProperty;
    private final StringProperty nameTeacherProperty;
    private final StringProperty nameSubjectProperty;
    private final StringProperty assignedRoomProperty;
    public EventFX() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.capacityProperty = new SimpleIntegerProperty();
        this.nameTeacherProperty = new SimpleStringProperty();
        this.nameSubjectProperty = new SimpleStringProperty();
        this.assignedRoomProperty = new SimpleStringProperty();
    }

    public IntegerProperty getIdProperty(){
        return idProperty;
    }
    public void setIdProp(int id){
        this.idProperty.set(id);
    }

    public StringProperty getNameProperty(){
        return nameProperty;
    }
    public void setNameProp(String name){
        this.nameProperty.set(name);
    }

    public IntegerProperty getCapacityProperty(){
        return capacityProperty;
    }
    public void setCapacityProp(int capacity){
        this.capacityProperty.set(capacity);
    }

    public StringProperty getNameTeacherProperty(){
        return nameTeacherProperty;
    }
    public void setNameTeacherProp(String nameTeacher){
        this.nameTeacherProperty.set(nameTeacher);
    }

    public StringProperty getNameSubjectProperty(){
        return nameSubjectProperty;
    }
    public void setNameSubjectProp(String nameSubject){
        this.nameSubjectProperty.set(nameSubject);
    }

    public StringProperty getAssignedRoomProperty(){
        return assignedRoomProperty;
    }

}
