package com.ericsson.networkdevice.dto;

/**
 * Data Transfer Object for managing subscriptions on network devices.
 * Contains information like the device's IP address, port, type of subscription, and polling interval.
 */
public class SubscriptionManagementDTO {
    private String address; // The IP address of the device
    private Integer port; // The port of the device
    private String subscriptionType;
    private Integer pollingInterval; // Interval in seconds for polling, applicable for periodic subscriptions

    /**
     * Default constructor, primarily used for JSON deserialization.
     */
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


}
