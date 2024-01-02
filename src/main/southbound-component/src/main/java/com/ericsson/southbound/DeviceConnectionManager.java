package com.ericsson.southbound;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages sessions with network devices in the Southbound component.
 * This class is responsible for creating, retrieving, and closing sessions for network devices.
 */
@Component
public class DeviceConnectionManager {

    // A map to hold sessions, indexed by a composite key of address and port
    private Map<String, Session> sessions = new HashMap<>();

    /**
     * Creates and stores a new session for a given network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return The newly created session.
     */
    public Session createSession(String address, int port) {
        String key = createKey(address, port);
        Session session = new Session(address, port);
        sessions.put(key, session);
        return session;
    }

    /**
     * Retrieves an existing session for a given network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return The session if found, or null otherwise.
     */
    public Session getSession(String address, int port) {
        String key = createKey(address, port);
        return sessions.get(key);
    }

    /**
     * Closes and removes a session for a given network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     */
    public void closeSession(String address, int port) {
        String key = createKey(address, port);
        sessions.remove(key);
    }

    /**
     * Helper method to create a unique key for indexing sessions in the map.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return A unique key combining the address and port.
     */
    private String createKey(String address, int port) {
        return address + ":" + port;
    }
}
