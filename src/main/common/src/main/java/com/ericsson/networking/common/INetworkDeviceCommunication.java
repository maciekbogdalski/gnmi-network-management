package com.ericsson.networking.common;

/**
 * This interface defines the core communication operations for network devices.
 */
public interface INetworkDeviceCommunication {

    /**
     * Fetches data from the device identified by the given deviceId.
     *
     * @param deviceId Identifier of the network device.
     * @param data Information or parameters required for the get operation.
     * @return The retrieved data.
     */
    Object get(String deviceId, Object data);


    /**
     * Sets or updates data on the device identified by the given deviceId.
     *
     * @param deviceId Identifier of the network device.
     * @param data Information or data to be set on the device.
     * @return Response or status of the set operation.
     */
    Object set(String deviceId, Object data);

    /**
     * Creates a subscription on the device for specific data or events.
     *
     * @param deviceId Identifier of the network device.
     * @param subscriptionInfo Information about the subscription being created.
     */
    void createSubscription(String deviceId, Object subscriptionInfo);

    /**
     * Cancels an active subscription.
     *
     * @param subscriptionId Identifier of the subscription to be canceled.
     */
    void cancelSubscription(String subscriptionId);
}
