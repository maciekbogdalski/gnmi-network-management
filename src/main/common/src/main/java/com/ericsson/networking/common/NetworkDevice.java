package com.ericsson.networking.common;

import java.util.Objects;

public class NetworkDevice {


    private String address;
    private int port;

    private String deviceId;
    private String configuration;  // Assuming configuration is a string. Adjust as needed.

    public NetworkDevice(String address, int port) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        if (port <= 0) {
            throw new IllegalArgumentException("Port must be positive.");
        }
        this.address = address;
        this.port = port;
    }
    // Existing constructor can be removed if not needed or kept if you have devices identified by a deviceId.
    public NetworkDevice(String deviceId) {
        // ... existing constructor code ...
    }

    public String getDeviceId() {
        return deviceId;
    }

    // New methods for retrieving and updating device configurations
    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NetworkDevice that = (NetworkDevice) obj;
        return Objects.equals(address, that.address) && port == that.port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }

    @Override
    public String toString() {
        return "NetworkDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", configuration='" + configuration + '\'' +
                '}';
    }
}
