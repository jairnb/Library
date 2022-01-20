module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires dotenv.java;

    opens com.example.library to javafx.fxml;
    exports com.example.library;
    exports com.example.library.controller;
    exports com.example.library.model.domain;
    exports com.example.library.model.dao;
    exports com.example.library.model.database;
    opens com.example.library.controller to javafx.fxml;

}