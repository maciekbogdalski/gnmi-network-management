package com.ericsson.southbound;
import com.ericsson.networking.common.Configuration;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a session with a network device.
 * This class handles simulated interactions with the device, managing its configuration and handling requests.
 */
public class Session {
    private static final Logger logger = LoggerFactory.getLogger(Session.class);


    private final String address; // IP address of the network device
    private final int port; // Port number of the network device
    private final String sessionId; // Unique identifier for the session
    private final Map<String, String> simulatedDeviceStore; // Simulates device's configuration store

    /**
     * Initializes a session with the network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     */
    public Session(String address, int port) {
        this.address = address;
        this.port = port;
        this.sessionId = UUID.randomUUID().toString();
        this.simulatedDeviceStore = new HashMap<>();
    }

    // Returns the sessionId
    public String getSessionId() {
        return sessionId;
    }

    // Returns the address associated with this session
    public String getAddress() {
        return address;
    }

    // Returns the port associated with this session
    public int getPort() {
        return port;
    }


    /**
     * Simulates sending a request to the network device and getting a response.
     * Handles different types of requests like configuration retrieval or setting.
     * @param requestType The type of request (e.g., "getConfiguration", "setConfiguration").
     * @param data The data related to the request, if applicable.
     * @return A Configuration object or an appropriate response based on the request.
     * @throws IllegalArgumentException if the input data format is incorrect.
     * @throws UnsupportedOperationException if the request type is not supported.
     */
    public Configuration sendRequest(String requestType, Object data) throws IllegalArgumentException, UnsupportedOperationException {
        switch (requestType) {
            case "getConfiguration":
                logger.debug("Session: Handling getConfiguration request.");
                return new Configuration(simulatedDeviceStore);

            case "setConfiguration":
                if (data instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> newConfig = (Map<String, String>) data;
                    simulatedDeviceStore.putAll(newConfig);
                    return new Configuration(simulatedDeviceStore);
                } else {
                    throw new IllegalArgumentException("Invalid configuration format, expected Map<String, String>.");
                }

            case "SUBSCRIBE":
                // Subscription logic...
                return null; // Or an appropriate success indicator

            case "UNSUBSCRIBE":
                // Unsubscription logic...
                return null; // Or an appropriate success indicator

            default:
                throw new UnsupportedOperationException("Unknown request type: " + requestType);
        }
    }




}
