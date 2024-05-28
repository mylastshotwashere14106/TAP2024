module com.example.tap2024 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.tap2024 to javafx.fxml;
    opens com.example.tap2024.vistas to javafx.fxml;
    exports com.example.tap2024;
    exports com.example.tap2024.vistas;

    requires java.sql;
    //requires mysql.connector.java;
    requires mariadb.java.client;
    requires org.kordamp.bootstrapfx.core;
}