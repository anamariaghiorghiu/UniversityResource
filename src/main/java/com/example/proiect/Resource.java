package com.example.proiect;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resource extends Table {
    private final StringProperty nameRoomProperty;
    private final StringProperty nameEventProperty;

    public Resource() {
        this.nameRoomProperty = new SimpleStringProperty();
        this.nameEventProperty = new SimpleStringProperty();
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
