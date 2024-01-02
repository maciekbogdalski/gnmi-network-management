package com.ericsson.networkdevice;
import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.SubscriptionManagementDTO;
import com.ericsson.networkdevice.dto.CapabilityResponseDTO;
import com.ericsson.networkdevice.gnmi.GnmiService;
import com.ericsson.networking.common.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.gnmi.proto.CapabilityResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import com.github.gnmi.proto.Encoding;
import com.ericsson.networkdevice.dto.Confirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Controller for handling GNMI (gRPC Network Management Interface) related operations.
 * Provides endpoints for device management, subscription handling, and GNMI capabilities.
 */
@RestController
@RequestMapping("/gnmi")
@Tag(name = "GNMI Controller", description = "GNMI related operations")
public class GnmiController {

    // Service dependencies...
    private final GnmiService gnmiService;
    private final NorthboundComponent northboundComponent;
    public static final Logger logger = LoggerFactory.getLogger(GnmiController.class);
    private boolean isConfigurationEmpty(Configuration configuration) {
        return configuration.getConfigEntries().isEmpty();
    }



    // Constructor...
    @Autowired
    public GnmiController(GnmiService gnmiService, NorthboundComponent northboundComponent) {
        this.gnmiService = gnmiService;
        this.northboundComponent = northboundComponent;
    }

    // Endpoint to get GNMI capabilities of a device.
    @GetMapping("/capabilities/{address}/{port}")
    public CapabilityResponseDTO getCapabilities(@PathVariable String address, @PathVariable int port) {
        CapabilityResponse capabilityResponse = gnmiService.getCapabilities(address, port);

        CapabilityResponseDTO dto = new CapabilityResponseDTO();
        dto.setGNMIVersion(capabilityResponse.getGNMIVersion());
        dto.setSupportedModels(capabilityResponse.getSupportedModelsList().stream()
                .map(modelData -> modelData.getName() + ":" + modelData.getVersion())
                .collect(Collectors.toList()));
        dto.setSupportedEncodings(capabilityResponse.getSupportedEncodingsList().stream()
                .map(Encoding::name)
                .collect(Collectors.toList()));

        return dto;
    }

    // Simple test endpoint to check if the service is running.
    @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }

    // Endpoint to add a network device.
    @PostMapping("/device")
    public ResponseEntity<String> manageDevice(@RequestBody DeviceManagementDTO deviceDTO) {
        try {
            Confirmation confirmation = northboundComponent.manageDevice("add", deviceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(confirmation.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to remove a network device.
    @DeleteMapping("/device")
    public ResponseEntity<String> removeDevice(@RequestBody DeviceManagementDTO deviceDTO) {
        try {
            Confirmation confirmation = northboundComponent.manageDevice("delete", deviceDTO);
            return ResponseEntity.ok(confirmation.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to create a subscription for a device.
    @PostMapping("/subscription")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionManagementDTO subscriptionDTO) {
        try {
            northboundComponent.manageSubscription("create", subscriptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to manage a subscription with a confirmation response.
    @PostMapping("/manageSubscription")
    public ResponseEntity<Confirmation> manageSubscription(
            @RequestParam String operation,
            @RequestBody SubscriptionManagementDTO subscriptionDTO) {
        try {
            Confirmation confirmation = northboundComponent.manageSubscriptionWithConfirmation(operation, subscriptionDTO);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            // Handle exceptions and return appropriate HTTP status and message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Confirmation(e.getMessage()));
        }
    }


    // Endpoint to cancel a subscription for a device.
    @DeleteMapping("/subscription")
    public ResponseEntity<String> cancelSubscription(@RequestBody SubscriptionManagementDTO subscriptionDTO) {
        try {
            northboundComponent.manageSubscription("delete", subscriptionDTO);
            return ResponseEntity.ok("Subscription canceled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to verify if a device is present in the system.
    @GetMapping("/verifyDevice/{address}/{port}")
    public ResponseEntity<String> verifyDevice(@PathVariable String address, @PathVariable int port) {
        boolean deviceExists = northboundComponent.isDevicePresent(address, port);
        if (deviceExists) {
            return ResponseEntity.ok("Success!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found.");
        }
    }

    // Endpoint to retrieve the configuration of a network device.
    @GetMapping("/deviceConfiguration/{address}/{port}")
    public ResponseEntity<String> getDeviceConfiguration(@PathVariable String address, @PathVariable int port) {
        String configuration = northboundComponent.getDeviceConfiguration(address, port);
        if (configuration != null) {
            return ResponseEntity.ok(configuration);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Configuration not found for device.");
        }
    }

    // Endpoint to update the configuration of a network device.
    @PostMapping("/configuration/{address}/{port}")
    public ResponseEntity<String> updateDeviceConfiguration(@PathVariable String address, @PathVariable int port, @RequestBody String newConfiguration) {
        try {
            northboundComponent.updateConfiguration(address, port, newConfiguration);
            return ResponseEntity.ok("Configuration updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating configuration: " + e.getMessage());
        }
    }











}
