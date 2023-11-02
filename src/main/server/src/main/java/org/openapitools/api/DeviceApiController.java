package org.openapitools.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ericsson.networkdevice.DeviceManager;
import com.ericsson.networking.common.NetworkDevice;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
public class DeviceApiController implements DeviceApi {

    private static final Logger log = LoggerFactory.getLogger(DeviceApiController.class);

    private final DeviceManager deviceManager;

    public DeviceApiController(DeviceManager deviceManager) {
        this.deviceManager = Objects.requireNonNull(deviceManager);
    }

    @Override
    public ResponseEntity<Void> deviceDelete(String deviceId) {
        try {
            deviceManager.removeDevice(deviceId);
            log.info("Device with ID {} removed successfully.", deviceId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error removing device with ID {}: {}", deviceId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> devicePost(String deviceId) {
        try {
            NetworkDevice device = new NetworkDevice(deviceId); // Assuming NetworkDevice has a constructor that accepts deviceId
            deviceManager.addDevice(device);
            log.info("Device added successfully.");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error adding device: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
