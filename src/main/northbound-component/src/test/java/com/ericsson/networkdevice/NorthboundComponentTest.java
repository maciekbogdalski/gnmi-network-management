package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.Confirmation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NorthboundComponentTest {

    @Autowired
    private NorthboundComponent northboundComponent;

    private DeviceManagementDTO deviceDTO;

    @BeforeEach
    void setUp() {
        deviceDTO = new DeviceManagementDTO();
        // Set properties of deviceDTO as needed
        deviceDTO.setAddress("localhost");
        deviceDTO.setPort(9315);
        // ... other properties
    }

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