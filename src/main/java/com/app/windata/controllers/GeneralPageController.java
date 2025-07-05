package com.app.windata.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Screen;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSSession;
import oshi.software.os.OperatingSystem;

import java.net.InetAddress;
import java.util.Locale;

public class GeneralPageController {
    @FXML
    private TextArea dataArea;
    @FXML
    public void initialize(){
        loadSystemData();
    }

    private void loadSystemData(){
        try{
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();
            HardwareAbstractionLayer hal = si.getHardware();

            String osData=os.toString();

            String laguage = Locale.getDefault().getDisplayLanguage();

            String architecture = System.getProperty("os.arch");
            String hostname= InetAddress.getLocalHost().getHostName();
            OSSession[] sessions = os.getSessions().toArray(new OSSession[0]);
            Long uptime = os.getSystemUptime();
            long h = uptime / 3600;
            long m = (uptime % 3600) / 60;
            long s = uptime % 60;


            StringBuilder data = new StringBuilder();
            data.append("Operating System: ").append(osData)
                    .append("Arhitecture: ").append(architecture).append("\n")
                    .append("Language: ").append(laguage).append("\n")
                    .append("HostName: ").append(hostname).append("\n");


            for(OSSession session:sessions){
                data.append("User: ").append(session.getUserName()).append("\n");
            }
            data .append("Uptime: ").append(h).append(':').append(m).append(':').append(s).append("\n");

            dataArea.setText(data.toString());




        } catch (Exception e) {
            dataArea.setText("Error loading system data: "+ e.getMessage());
        }
    }
}
