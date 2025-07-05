package com.app.windata.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Screen;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.io.IOException;
import oshi.software.os.FileSystem;


public class ComponentsPageController {



    @FXML
    private TextArea dataArea;
    @FXML
    public void initialize() throws IOException {
        loadSystemData();
    }

    private void loadSystemData() throws IOException {
        try {
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();
            HardwareAbstractionLayer hal = si.getHardware();
            CentralProcessor cpu = hal.getProcessor();

            double memoryDbl = (double) hal.getMemory().getTotal() / (1024 * 1024 * 1024);
            String memory = Math.ceil(memoryDbl) + "GB";
            String gpu = hal.getGraphicsCards().get(0).getName();

            StringBuilder data = new StringBuilder();
            String cpuName = cpu.getProcessorIdentifier().getName();
            double baseGHz = cpu.getProcessorIdentifier().getVendorFreq() / 1_000_000_000.0;

            FileSystem fs = os.getFileSystem();

            data.append("CPU: ").append(cpuName).append("\n")
                    .append("Base Clock: ").append(baseGHz > 0 ? String.format("%.2f GHz", baseGHz) : "Unknown").append('\n')
                    .append("Memory: ").append(memory).append("\n")
                    .append("GPU: ").append(gpu).append("\n");


            for (OSFileStore store : fs.getFileStores()) {
                data.append("Storage: ").append(store.getName()).append('\n')
                        .append("    Total: ").append(String.format("%.2f GB", store.getTotalSpace() / 1e9)).append('\n')
                        .append("    Free:  ").append(String.format("%.2f GB", store.getUsableSpace() / 1e9)).append('\n');
            }
            dataArea.setText(data.toString());
        } catch (Exception e) {
            dataArea.setText("Error loading System Data: "+e.getMessage());
        }


    }



}
