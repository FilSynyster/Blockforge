module agenda {

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;

    requires ormlite.jdbc;

    requires java.sql;
    requires io.github.classgraph;
    requires jakarta.persistence;

    opens agenda.app.models to ormlite.jdbc;
    opens agenda.app.view.controller to javafx.fxml;

    exports agenda.app;
    exports agenda.database.core;
    exports agenda.database.ormlite;
    exports agenda.database.sql;
    exports  agenda.database.ormlite.configuration;
    exports agenda.database.exceptions;
}