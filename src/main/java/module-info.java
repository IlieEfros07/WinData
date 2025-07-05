module com.app.windata {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.github.oshi;
    requires java.net.http;
    requires com.sun.jna.platform;


    opens com.app.windata to javafx.fxml;
    exports com.app.windata;
    exports com.app.windata.controllers;
    opens com.app.windata.controllers to javafx.fxml;
}