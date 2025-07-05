package com.app.windata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/MainData.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm());
        stage.getIcons().add(new Image(
                Objects.requireNonNull(getClass().getResource("/images/appicon.ico")).toExternalForm()));

        stage.setTitle("WinData");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}