package com.ericsson.networkdevice;

import com.ericsson.networking.common.NetworkDevice;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class DeviceManager {

    private final Map<String, NetworkDevice> devices = new ConcurrentHashMap<>();

    private String createDeviceKey(String address, int port) {
        return address + ":" + port; // Create a composite key based on IP address and port
    }

    public void addDevice(NetworkDevice device) {
        String key = createDeviceKey(device.getAddress(), device.getPort());
        devices.put(key, device);
    }

    public void removeDevice(String address, int port) {
        String key = createDeviceKey(address, port);
        devices.remove(key);
    }

    public NetworkDevice getDevice(String address, int port) {
        String key = createDeviceKey(address, port);
        return devices.get(key);
    }

    // Methods for device configuration might use the IP and port to identify the device
    public String getDeviceConfiguration(String address, int port) {
        NetworkDevice device = getDevice(address, port);
        return device != null ? device.getConfiguration() : null;
    }

    public void updateDeviceConfiguration(String address, int port, String configuration) {
        NetworkDevice device = getDevice(address, port);
        if (device != null) {
            device.setConfiguration(configuration);
        }
    }
}
