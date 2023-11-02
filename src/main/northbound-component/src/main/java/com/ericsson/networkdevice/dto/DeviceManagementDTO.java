package com.ericsson.networkdevice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for managing network devices.
 * This class includes information necessary for adding a device to the management system,
 * including its network address, network port, and initial configuration.
 */
public class DeviceManagementDTO {

    // Removed the deviceId field as we are now using IP address and port as unique identifiers

    @NotEmpty(message = "Device type is required and cannot be empty.")
    private String deviceType;

    // This field might be optional based on whether you require an initial configuration for every device.
    private String initialConfiguration;

    @NotEmpty(message = "Network address is required and cannot be empty.")
    private String address; // This is the network address (IP address) of the device.

    @NotNull(message = "Network port is required.")
    private Integer port; // This is the network port of the device.

    // Default constructor for JSON deserialization
    public DeviceManagementDTO() {}

    // Getters and Setters

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getInitialConfiguration() {
        return initialConfiguration;
    }

    public void setInitialConfiguration(String initialConfiguration) {
        this.initialConfiguration = initialConfiguration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
