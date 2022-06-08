module com.example.proiect {
    requires javafx.controls;
    requires javafx.fxml;


    requires javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.jsoup;
    opens com.example.proiect to javafx.fxml;
    exports com.example.proiect;
}