package org.openapitools.api;

import org.openapitools.model.ConfigurationGet200Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ericsson.networkdevice.DeviceManager;
import com.ericsson.networking.common.INetworkDeviceCommunication;

@Controller
@RequestMapping("${openapi.northbound.base-path:}")
public class ConfigurationApiController implements ConfigurationApi {

    private final DeviceManager deviceManager;
    private final INetworkDeviceCommunication networkDeviceCommunication;

    @Autowired
    public ConfigurationApiController(DeviceManager deviceManager, INetworkDeviceCommunication networkDeviceCommunication) {
        this.deviceManager = deviceManager;
        this.networkDeviceCommunication = networkDeviceCommunication;
    }

    @Override
    public ResponseEntity<ConfigurationGet200Response> getDeviceConfiguration(String deviceId) {
        try {
            String configurationData = (String) networkDeviceCommunication.get(deviceId, null); // Cast to String

            ConfigurationGet200Response configurationResponse = new ConfigurationGet200Response();
            configurationResponse.setConfiguration(configurationData);

            return new ResponseEntity<>(configurationResponse, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> setDeviceConfiguration(String deviceId, String configuration) {
        try {
            networkDeviceCommunication.set(deviceId, configuration);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
