package com.ericsson.southbound;

import com.ericsson.networking.common.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class SouthboundComponent {
    private static final Logger logger = LoggerFactory.getLogger(SouthboundComponent.class);


    private DeviceConnectionManager deviceConnectionManager;

    public SouthboundComponent(DeviceConnectionManager deviceConnectionManager) {
        this.deviceConnectionManager = Objects.requireNonNull(deviceConnectionManager, "DeviceConnectionManager cannot be null");
    }

    public Configuration getConfiguration(String address, int port) {
        logger.debug("SouthboundComponent: Getting configuration for address {}, port {}", address, port);
        Session session = getOrCreateSession(address, port);

        // Assuming the device's session knows how to handle a "getConfiguration" request
        // The sendRequest method should return an object of type Configuration
        Configuration configuration = (Configuration) session.sendRequest("getConfiguration", null);
        if (configuration == null) {
            logger.error("Failed to retrieve configuration for device at address {}, port {}", address, port);
            throw new IllegalStateException("Configuration could not be retrieved.");
        }

        return configuration;
    }

    public void setConfiguration(String address, int port, Configuration configuration) {
        // Get the session for the device using address and port
        Session session = getOrCreateSession(address, port);
        // Adjust the sendRequest method call to accept a Configuration object directly
        session.sendRequest("setConfiguration", configuration);
    }

    public void createSubscription(String address, int port, Object subscriptionInfo) {
        Session session = getOrCreateSession(address, port);
        // Assume subscriptionInfo is a serializable object that the session can handle
        session.sendRequest("SUBSCRIBE", subscriptionInfo);
    }

    public void cancelSubscription(String address, int port) {
        Session session = getOrCreateSession(address, port);
        // Use a unique identifier for the subscription based on address and port, if needed
        session.sendRequest("UNSUBSCRIBE", null);
    }

    private Session getOrCreateSession(String address, int port) {
        Session session = deviceConnectionManager.getSession(address, port);
        if (session == null) {
            session = deviceConnectionManager.createSession(address, port);
        }
        return session;
    }
}
