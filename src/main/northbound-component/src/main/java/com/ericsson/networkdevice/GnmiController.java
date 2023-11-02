package com.ericsson.networkdevice;
import com.ericsson.networkdevice.dto.DeviceManagementDTO;
import com.ericsson.networkdevice.dto.SubscriptionManagementDTO;
import com.ericsson.networkdevice.dto.CapabilityResponseDTO;
import com.ericsson.networkdevice.gnmi.GnmiService;
import com.github.gnmi.proto.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.gnmi.proto.CapabilityRequest;
import com.github.gnmi.proto.CapabilityResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.github.gnmi.proto.Encoding;
import com.ericsson.networkdevice.dto.Confirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/gnmi")
@Tag(name = "GNMI Controller", description = "GNMI related operations")
public class GnmiController {

    private final GnmiService gnmiService;

    private final NorthboundComponent northboundComponent;
    private static final Logger logger = LoggerFactory.getLogger(GnmiController.class);




    @Autowired
    public GnmiController(GnmiService gnmiService, NorthboundComponent northboundComponent) {
        this.gnmiService = gnmiService;
        this.northboundComponent = northboundComponent;
    }

    @GetMapping("/capabilities")
    public CapabilityResponseDTO getCapabilities() {
        CapabilityResponse capabilityResponse = gnmiService.getCapabilities();

        CapabilityResponseDTO dto = new CapabilityResponseDTO();

        // Assuming there's a correct method to get the gNMI version:
        dto.setGNMIVersion(capabilityResponse.getGNMIVersion());

        // Convert ModelData list to a list of strings:
        List<String> supportedModels = capabilityResponse.getSupportedModelsList().stream()
                .map(modelData -> modelData.getName() + ":" + modelData.getVersion())  // Assuming name and version representation
                .collect(Collectors.toList());
        dto.setSupportedModels(supportedModels);

        // Convert Encoding enum list to a list of strings:
        List<String> supportedEncodings = capabilityResponse.getSupportedEncodingsList().stream()
                .map(Encoding::name)
                .collect(Collectors.toList());
        dto.setSupportedEncodings(supportedEncodings);

        return dto;
    }


//    @PostMapping("/getData")
//    public GetResponse getGnmiData(@RequestBody Map<String, String> requestBody) {
//        String path = requestBody.get("path");
//        return gnmiService.getGnmiData(path);
//    }

//    @PostMapping("/getData")
//    public ResponseEntity<String> getGnmiData(@RequestBody Map<String, String> requestBody) {
//        logger.info("Received /getData request");
//        return ResponseEntity.ok("GNMI Data endpoint reached successfully.");
//    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }

    @PostMapping("/device")
    public ResponseEntity<String> manageDevice(@RequestBody DeviceManagementDTO deviceDTO) {
        try {
            northboundComponent.manageDevice("add", deviceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Device added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/device")
    public ResponseEntity<String> removeDevice(@RequestBody DeviceManagementDTO deviceDTO) {
        try {
            northboundComponent.manageDevice("delete", deviceDTO);
            return ResponseEntity.ok("Device removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/subscription")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionManagementDTO subscriptionDTO) {
        try {
            northboundComponent.manageSubscription("create", subscriptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

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



    @DeleteMapping("/subscription")
    public ResponseEntity<String> cancelSubscription(@RequestBody SubscriptionManagementDTO subscriptionDTO) {
        try {
            northboundComponent.manageSubscription("delete", subscriptionDTO);
            return ResponseEntity.ok("Subscription canceled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
