package com.ericsson.networkdevice;
import com.ericsson.networking.common.NetworkDevice;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DeviceManager is responsible for maintaining a registry of network devices.
 * It provides functionalities to add, remove, retrieve, and update device configurations.
 */
@Service
public class DeviceManager {

    // ConcurrentHashMap to store network devices. It provides thread-safe operations.
    private final Map<String, NetworkDevice> devices = new ConcurrentHashMap<>();

    /**
     * Generates a unique key for each device using its address and port.
     * This key is used to identify devices in the ConcurrentHashMap.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return A composite key based on IP address and port.
     */
    private String createDeviceKey(String address, int port) {
        return address + ":" + port; // Create a composite key based on IP address and port
    }

    /**
     * Adds a network device to the device registry.
     * @param device The NetworkDevice object to be added.
     */
    public void addDevice(NetworkDevice device) {
        String key = createDeviceKey(device.getAddress(), device.getPort());
        devices.put(key, device);
    }

    /**
     * Removes a network device from the device registry using its address and port.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     */
    public void removeDevice(String address, int port) {
        String key = createDeviceKey(address, port);
        devices.remove(key);
    }

    /**
     * Retrieves a network device from the registry using its address and port.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return The NetworkDevice object if found, or null otherwise.
     */
    public NetworkDevice getDevice(String address, int port) {
        String key = createDeviceKey(address, port);
        return devices.get(key);
    }

    /**
     * Gets the configuration of a network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @return The configuration of the network device or null if the device is not found.
     */
    public String getDeviceConfiguration(String address, int port) {
        NetworkDevice device = getDevice(address, port);
        return device != null ? device.getConfiguration() : null;
    }

    /**
     * Updates the configuration of a network device.
     * @param address The IP address of the network device.
     * @param port The port number of the network device.
     * @param configuration The new configuration to be set on the device.
     */
    public void updateDeviceConfiguration(String address, int port, String configuration) {
        NetworkDevice device = getDevice(address, port);
        if (device != null) {
            device.setConfiguration(configuration);
        }
    }
}
