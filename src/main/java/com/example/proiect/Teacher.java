package com.example.proiect;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher extends Table{
    private StringProperty gradDidacticProperty;

    public Teacher() {
        super();
        this.gradDidacticProperty = new SimpleStringProperty();
    }

    public StringProperty getGradDidacticProperty() {
        return gradDidacticProperty;
    }
    public void setGradDidacticProp(String grad) {
        this.gradDidacticProperty.set(grad);
    }

}
