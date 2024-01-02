package com.ericsson.networking.common;

import java.util.Objects;

/**
 * Represents a network device with its address, port, and configuration.
 */
public class NetworkDevice {


    private String address; // IP address of the network device
    private int port; // Port number of the network device

    private String deviceId; // Unique identifier for the network device
    private String configuration;  // Configuration of the network device


    /**
     * Constructor for NetworkDevice with address and port.
     * @param address IP address of the network device.
     * @param port Port number of the network device.
     * @throws IllegalArgumentException if address is null/empty or port is non-positive.
     */
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

    // Override equals, hashCode, and toString methods for proper object comparison and representation
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
