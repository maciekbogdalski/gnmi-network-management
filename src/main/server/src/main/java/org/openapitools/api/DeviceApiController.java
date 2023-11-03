package org.openapitools.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ericsson.networkdevice.DeviceManager;
import com.ericsson.networking.common.NetworkDevice;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class DeviceApiController implements DeviceApi {

    private static final Logger log = LoggerFactory.getLogger(DeviceApiController.class);

    private final DeviceManager deviceManager;

    public DeviceApiController(DeviceManager deviceManager) {
        this.deviceManager = Objects.requireNonNull(deviceManager, "DeviceManager cannot be null");
    }

    @Override
    public ResponseEntity<Void> deviceDelete(String address, int port) {
        try {
            deviceManager.removeDevice(address, port);
            log.info("Device with address {} and port {} removed successfully.", address, port);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error removing device with address {} and port {}: {}", address, port, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> devicePost(String address, int port) {
        try {
            NetworkDevice device = new NetworkDevice(address, port); // Update constructor if necessary
            deviceManager.addDevice(device);
            log.info("Device with address {} and port {} added successfully.", address, port);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error adding device with address {} and port {}: {}", address, port, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
