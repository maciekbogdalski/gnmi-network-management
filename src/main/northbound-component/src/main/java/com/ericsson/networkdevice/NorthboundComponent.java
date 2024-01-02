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




/**
 * The NorthboundComponent class serves as the interface for external clients.
 * It processes requests related to device management, subscription management, and configuration.
 * Interacts with DeviceManager for device catalog operations and SouthboundComponent for device communication.
 */
@Service
public class NorthboundComponent {

    // Dependency injections for various components used in this class.
    @Autowired
    private DeviceManager deviceManager;
    @Autowired
    private GnmiServerManager gnmiServerManager; // Manages gNMI server instances.

    @Autowired
    private SouthboundComponent southboundComponent;

    // Constructor for NorthboundComponent
    public NorthboundComponent(DeviceManager deviceManager, SouthboundComponent southboundComponent) {
        this.deviceManager = deviceManager;
        this.southboundComponent = southboundComponent;
    }

    /**
     * Manages the addition or deletion of network devices.
     * @param operation The operation type (add/delete).
     * @param deviceInfoDTO Data transfer object containing device information.
     * @return Confirmation of the operation's outcome.
     */
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


    /**
     * Checks if a device is present in the device manager.
     * @param address The IP address of the device.
     * @param port The port number of the device.
     * @return true if the device is present, false otherwise.
     */
    public boolean isDevicePresent(String address, int port) {
        return deviceManager.getDevice(address, port) != null;
    }



    /**
     * Manages subscriptions and returns a confirmation message.
     * @param operation The subscription operation (create/delete).
     * @param subscriptionDTO Data transfer object containing subscription details.
     * @return Confirmation of the subscription operation's completion.
     */
    public Confirmation manageSubscriptionWithConfirmation(String operation, SubscriptionManagementDTO subscriptionDTO) {
        manageSubscription(operation, subscriptionDTO);
        return new Confirmation("Subscription " + operation + " operation completed successfully for device at IP: " + subscriptionDTO.getAddress() + " and port: " + subscriptionDTO.getPort());
    }


    /**
     * Manages subscriptions without returning a confirmation message.
     * @param operation The subscription operation (create/delete).
     * @param subscriptionDTO Data transfer object containing subscription details.
     */
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


    /**
     * Retrieves the configuration of a specified network device.
     * @param address The IP address of the device.
     * @param port The port number of the device.
     * @return The device's configuration.
     */
    public String getDeviceConfiguration(String address, int port) {
        return deviceManager.getDeviceConfiguration(address, port);
    }


    /**
     * Updates the configuration of a specified network device.
     * @param address The IP address of the device.
     * @param port The port number of the device.
     * @param newConfiguration The new configuration to be set on the device.
     */
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
