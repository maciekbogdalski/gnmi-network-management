package com.ericsson.southbound;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class DeviceConnectionManager {

    // Sessions indexed by a composite key of address and port
    private Map<String, Session> sessions = new HashMap<>();

    // Creates a new session for a given address and port
    public Session createSession(String address, int port) {
        String key = createKey(address, port);
        Session session = new Session(address, port);
        sessions.put(key, session);
        return session;
    }

    // Retrieves a session using its address and port
    public Session getSession(String address, int port) {
        String key = createKey(address, port);
        return sessions.get(key);
    }

    // Closes a session using its address and port
    public void closeSession(String address, int port) {
        String key = createKey(address, port);
        sessions.remove(key);
    }

    // Helper method to create a unique key for the sessions map
    private String createKey(String address, int port) {
        return address + ":" + port;
    }
}
