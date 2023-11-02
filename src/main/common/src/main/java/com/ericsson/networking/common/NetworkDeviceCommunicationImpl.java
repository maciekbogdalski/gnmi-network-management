package com.ericsson.networking.common;

import org.springframework.stereotype.Service;

@Service
public class NetworkDeviceCommunicationImpl implements INetworkDeviceCommunication {

    @Override
    public Object get(String deviceId, Object data) {
        // Your implementation here
        return null; // Placeholder return
    }

    @Override
    public Object set(String deviceId, Object data) {
        // Your implementation here
        return null; // Placeholder return
    }

    @Override
    public void createSubscription(String deviceId, Object subscriptionInfo) {
        // Your implementation here
    }

    @Override
    public void cancelSubscription(String subscriptionId) {
        // Your implementation here
    }
}
