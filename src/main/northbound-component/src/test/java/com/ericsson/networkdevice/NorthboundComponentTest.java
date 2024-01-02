package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.Confirmation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NorthboundComponent.
 * Validates the functionality of device management operations like adding or removing devices.
 */
@SpringBootTest
public class NorthboundComponentTest {

    @Autowired
    private NorthboundComponent northboundComponent;

    private DeviceManagementDTO deviceDTO;

    /**
     * Sets up test data before each test.
     * Initializes a DeviceManagementDTO object with predefined data for testing.
     */
    @BeforeEach
    void setUp() {
        deviceDTO = new DeviceManagementDTO();
        // Set properties of deviceDTO as needed
        deviceDTO.setAddress("localhost");
        deviceDTO.setPort(9315);
        // ... other properties
    }

    /**
     * Tests the 'add device' functionality of the NorthboundComponent.
     * Verifies if the device is added successfully and if the correct confirmation message is returned.
     */
    @Test
    void whenAddDevice_thenDeviceIsAdded() {
        // Act
        Confirmation confirmation = northboundComponent.manageDevice("add", deviceDTO);

        // Assert
        String expectedMessage = "Device added successfully with GNMI server at localhost:9315";
        assertEquals(expectedMessage, confirmation.getMessage());
    }

    // Additional tests...
}