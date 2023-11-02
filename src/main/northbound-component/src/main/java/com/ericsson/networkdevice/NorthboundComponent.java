package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.Confirmation;
import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.SubscriptionManagementDTO;
import com.ericsson.networking.common.NetworkDevice;
import com.ericsson.networking.common.Configuration;
import com.ericsson.southbound.SouthboundComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NorthboundComponent {

    @Autowired
    private DeviceManager deviceManager;

    @Autowired
    private SouthboundComponent southboundComponent;

    public NorthboundComponent(DeviceManager deviceManager, SouthboundComponent southboundComponent) {
        this.deviceManager = deviceManager;
        this.southboundComponent = southboundComponent;
    }

    public Confirmation manageDevice(String operation, DeviceManagementDTO deviceInfoDTO) {
        Confirmation confirmation;
        if ("add".equals(operation)) {
            NetworkDevice device = new NetworkDevice(deviceInfoDTO.getAddress() + ":" + deviceInfoDTO.getPort());
            device.setAddress(deviceInfoDTO.getAddress());
            device.setPort(deviceInfoDTO.getPort());
            device.setConfiguration(deviceInfoDTO.getInitialConfiguration());
            deviceManager.addDevice(device);
            confirmation = new Confirmation("Device added successfully with IP: " + device.getAddress() + " and port: " + device.getPort());
        } else if ("delete".equals(operation)) {
            NetworkDevice device = deviceManager.getDevice(deviceInfoDTO.getAddress(), deviceInfoDTO.getPort());
            if (device != null) {
                deviceManager.removeDevice(device.getAddress(), device.getPort());
                confirmation = new Confirmation("Device deleted successfully with IP: " + device.getAddress() + " and port: " + device.getPort());
            } else {
                throw new IllegalArgumentException("Device not found for IP: " + deviceInfoDTO.getAddress() + " and port: " + deviceInfoDTO.getPort());
            }
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
        return confirmation;
    }


    public Confirmation manageSubscriptionWithConfirmation(String operation, SubscriptionManagementDTO subscriptionDTO) {
        manageSubscription(operation, subscriptionDTO);
        return new Confirmation("Subscription " + operation + " operation completed successfully for device at IP: " + subscriptionDTO.getAddress() + " and port: " + subscriptionDTO.getPort());
    }


    public void manageSubscription(String operation, SubscriptionManagementDTO subscriptionDTO) {
        NetworkDevice device = deviceManager.getDevice(subscriptionDTO.getAddress(), subscriptionDTO.getPort());
        if (device == null) {
            throw new IllegalArgumentException("Device not found with IP: " + subscriptionDTO.getAddress() + " and port: " + subscriptionDTO.getPort());
        }

        if ("create".equals(operation)) {
            // Assuming createSubscription expects the device object itself
            southboundComponent.createSubscription(subscriptionDTO.getAddress(), subscriptionDTO.getPort(), device);
        } else if ("delete".equals(operation)) {
            // Assuming cancelSubscription only needs address and port
            southboundComponent.cancelSubscription(subscriptionDTO.getAddress(), subscriptionDTO.getPort());
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }


    public Configuration getConfiguration(String address, int port) {
        NetworkDevice device = deviceManager.getDevice(address, port);
        if (device == null) {
            throw new IllegalArgumentException("Device not found with IP: " + address + " and port: " + port);
        }
        return southboundComponent.getConfiguration(address, port); // Adjusted method call
    }

    public void setConfiguration(String address, int port, Configuration configuration) {
        NetworkDevice device = deviceManager.getDevice(address, port);
        if (device == null) {
            throw new IllegalArgumentException("Device not found with IP: " + address + " and port: " + port);
        }
        southboundComponent.setConfiguration(address, port, configuration); // Adjusted method call
    }

    // ... any additional methods required by the NorthboundComponent ...

}
