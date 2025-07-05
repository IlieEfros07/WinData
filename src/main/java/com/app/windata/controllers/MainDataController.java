package com.app.windata.controllers;


import com.app.windata.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

public class MainDataController {


    @FXML
    public AnchorPane contentPane;

    private void loadPage(String fxml){
        try{
            Parent root= FXMLLoader.load(MainApplication.class.getResource("fxml/"+fxml));
            contentPane.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showGeneral(){
        loadPage("general_page.fxml");
    }
    @FXML
    public void showComponents(){
        loadPage("components_page.fxml");
    }
    @FXML
    public void showNetwork(){
        loadPage("network_page.fxml");
    }






}
