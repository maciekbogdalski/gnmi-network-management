package com.ericsson.southbound;
import com.ericsson.networking.common.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Session {

    private final String address;
    private final int port;
    private final String sessionId;
    private final Map<String, String> simulatedDeviceStore; // Simulates device's configuration store

    // Constructor initializes a session with a unique sessionId, address, and port
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

    // Simulates sending a request to the network device and getting a response
    // Simulates sending a request to the network device and getting a response
    public Configuration sendRequest(String requestType, Object data) throws IllegalArgumentException, UnsupportedOperationException {
        switch (requestType) {
            case "getConfiguration":
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
