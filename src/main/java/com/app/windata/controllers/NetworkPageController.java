package com.app.windata.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;

import java.net.NetworkInterface;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class NetworkPageController {

    @FXML
    private TextArea dataArea;

    @FXML
    public void initialize() {
        loadNetworkData();
    }

    private void loadNetworkData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.ipify.org/"))
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                SystemInfo si = new SystemInfo();
                OperatingSystem os = si.getOperatingSystem();
                List<NetworkIF> networkIFs = si.getHardware().getNetworkIFs();

                String ipAddress = response.body();

                NetworkIF netIf = networkIFs.get(0);

                String mac = netIf.getMacaddr();
                String face = netIf.getName();
                boolean isUp = netIf.getIfOperStatus() == NetworkIF.IfOperStatus.UP;
                String status = isUp ? "Connected" : "Disconnected";
                String bytesSent = formatBytes(netIf.getBytesSent());
                String bytesRecv = formatBytes(netIf.getBytesRecv());


                StringBuilder data = new StringBuilder();
                data.append("Interface: ").append(face).append("\n")
                        .append("MAC: ").append(mac).append("\n")
                        .append("Status: ").append(status).append("\n")
                        .append("Bytes Sent: ").append(bytesSent).append("\n")
                        .append("Bytes Received: ").append(bytesRecv).append("\n")
                        .append("Public IP: ").append(ipAddress).append("\n");


                Platform.runLater(() -> dataArea.setText(data.toString()));

            } catch (Exception e) {
                Platform.runLater(() -> dataArea.setText("Failed to retrieve IP\n" + e.getMessage()));
            }
        }).start();
    }
    private String formatBytes(long bytes) {
        double gb = bytes / 1024.0 / 1024.0 / 1024.0;
        return String.format("%.2f GB", gb);
    }
}
