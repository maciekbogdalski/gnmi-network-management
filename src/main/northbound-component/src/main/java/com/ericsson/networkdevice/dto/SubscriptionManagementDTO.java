package com.ericsson.networkdevice.dto;

public class SubscriptionManagementDTO {
    private String address; // The IP address of the device
    private Integer port; // The port of the device
    private String subscriptionType;
    private Integer pollingInterval;

    // Default constructor for JSON deserialization
    public SubscriptionManagementDTO() {}

    // Getters and Setters
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

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Integer getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(Integer pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    // You might want to remove the deviceId getter and setter,
    // or repurpose them if the deviceId is still needed for other operations.
}
