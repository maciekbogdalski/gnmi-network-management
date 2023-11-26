package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.Confirmation;
import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.SubscriptionManagementDTO;
import com.ericsson.networking.common.NetworkDevice;
import com.ericsson.networking.common.GnmiServerManager;
import com.ericsson.southbound.SouthboundComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class NorthboundComponent {

    @Autowired
    private DeviceManager deviceManager;
    @Autowired
    private GnmiServerManager gnmiServerManager; // You will inject or instantiate this

    @Autowired
    private SouthboundComponent southboundComponent;

    public NorthboundComponent(DeviceManager deviceManager, SouthboundComponent southboundComponent) {
        this.deviceManager = deviceManager;
        this.southboundComponent = southboundComponent;
    }

    public Confirmation manageDevice(String operation, DeviceManagementDTO deviceInfoDTO) {
        try {
            if ("add".equals(operation)) {
                // Logic to add the device
                NetworkDevice device = new NetworkDevice(deviceInfoDTO.getAddress() + ":" + deviceInfoDTO.getPort());
                device.setAddress(deviceInfoDTO.getAddress());
                device.setPort(deviceInfoDTO.getPort());
                device.setConfiguration(deviceInfoDTO.getInitialConfiguration());
                deviceManager.addDevice(device);
                // Start a new gNMI server instance using the address and port
                String gnmiServerAddress = gnmiServerManager.startNewGnmiServerInstance(device.getAddress(), device.getPort());
                return new Confirmation("Device added successfully with GNMI server at " + gnmiServerAddress);
            } else if ("delete".equals(operation)) {
                // Logic to delete the device
                NetworkDevice device = deviceManager.getDevice(deviceInfoDTO.getAddress(), deviceInfoDTO.getPort());
                if (device != null) {
                    deviceManager.removeDevice(device.getAddress(), device.getPort());
                    // Stop the gNMI server instance using the address and port
                    gnmiServerManager.stopGnmiServerInstance(device.getAddress(), device.getPort());
                    return new Confirmation("Device and GNMI server instance removed successfully.");
                } else {
                    return new Confirmation("Device not found for IP: " + deviceInfoDTO.getAddress() + " and port: " + deviceInfoDTO.getPort());
                }
            } else {
                return new Confirmation("Invalid operation: " + operation);
            }
        } catch (IOException | InterruptedException e) {
            // Log the exception and return an error confirmation
            // logger.error("Error managing GNMI server instance", e);
            return new Confirmation("Error managing GNMI server instance: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Handle the case where an invalid operation is given
            return new Confirmation(e.getMessage());
        }
    }

    public boolean isDevicePresent(String address, int port) {
        return deviceManager.getDevice(address, port) != null;
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


    public String getDeviceConfiguration(String address, int port) {
        return deviceManager.getDeviceConfiguration(address, port);
    }

    public void updateConfiguration(String address, int port, String newConfiguration) {
        // Validate input, handle exceptions as needed
        NetworkDevice device = deviceManager.getDevice(address, port);
        if (device == null) {
            throw new IllegalArgumentException("Device not found.");
        }
        device.setConfiguration(newConfiguration);
        // If there is any additional logic to handle after setting configuration, add it here.
    }

}
