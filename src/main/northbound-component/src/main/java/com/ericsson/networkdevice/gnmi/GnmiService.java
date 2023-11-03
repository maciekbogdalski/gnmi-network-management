package com.ericsson.networkdevice.gnmi;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.NettyChannelBuilder;
import com.github.gnmi.proto.gNMIGrpc;
import com.github.gnmi.proto.GetRequest;
import com.github.gnmi.proto.GetResponse;
import org.springframework.stereotype.Service;
import com.github.gnmi.proto.CapabilityRequest;
import com.github.gnmi.proto.CapabilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

@Service
public class GnmiService {

    private final ManagedChannel channel;
    private final gNMIGrpc.gNMIBlockingStub gnmiClient;

    private static final Logger logger = LoggerFactory.getLogger(GnmiService.class);  // <-- Create a logger instance

    public GnmiService() {
        this.channel = NettyChannelBuilder.forAddress("localhost", 9339)
                .usePlaintext()
                .build();
        this.gnmiClient = gNMIGrpc.newBlockingStub(channel);
    }

    public GetResponse getGnmiData(String path) {
        // Validate input
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        // Build GNMI Path
        com.github.gnmi.proto.Path.Builder pathBuilder = com.github.gnmi.proto.Path.newBuilder();
        for (String elem : path.split("[/:]")) {
            if (!elem.isEmpty()) {
                pathBuilder.addElem(com.github.gnmi.proto.PathElem.newBuilder().setName(elem).build());
            }
        }

        // Construct GNMI GetRequest
        GetRequest request = GetRequest.newBuilder().addPath(pathBuilder.build()).build();
        logger.info("Constructed GNMI Path: {}", pathBuilder.build().toString());

        // Execute GNMI call with a deadline
        try {
            return gnmiClient.withDeadlineAfter(10, TimeUnit.SECONDS).get(request);
        } catch (StatusRuntimeException e) {
            logger.error("gRPC call failed", e);
            throw new RuntimeException("gRPC call failed", e);
        }
    }

    public CapabilityResponse getCapabilities() {
        CapabilityRequest request = CapabilityRequest.newBuilder().build();
        try {
            CapabilityResponse response = gnmiClient.capabilities(request);
            logger.info("Fetched capabilities: {}", response.toString());
            return response;
        } catch (Exception e) {
            logger.error("Failed to fetch capabilities", e);
            // Rethrow as a custom exception
            throw new CapabilitiesFetchException("Failed to fetch capabilities", e);
        }
    }

    public class CapabilitiesFetchException extends RuntimeException {
        public CapabilitiesFetchException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public GetResponse getDeviceConfiguration(String deviceId) {
        // Validate the deviceId input
        if (deviceId == null || deviceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Device ID cannot be null or empty");
        }

        // Build the GNMI Path for the device configuration
        // Assuming the path for configuration is in the format: /devices/{deviceId}/config
        com.github.gnmi.proto.Path.Builder pathBuilder = com.github.gnmi.proto.Path.newBuilder()
                .addElem(com.github.gnmi.proto.PathElem.newBuilder().setName("devices").build())
                .addElem(com.github.gnmi.proto.PathElem.newBuilder().setName(deviceId).build())
                .addElem(com.github.gnmi.proto.PathElem.newBuilder().setName("config").build());

        // Construct the GNMI GetRequest
        GetRequest request = GetRequest.newBuilder()
                .addPath(pathBuilder.build())
                .setType(com.github.gnmi.proto.GetRequest.DataType.ALL) // Assuming we want all data
                .setEncoding(com.github.gnmi.proto.Encoding.JSON) // Assuming JSON encoding
                .build();

        logger.info("Constructed GNMI GetRequest for Device Configuration: {}", request);

        // Execute GNMI call with a deadline
        try {
            return gnmiClient.withDeadlineAfter(10, TimeUnit.SECONDS).get(request);
        } catch (StatusRuntimeException e) {
            logger.error("gRPC call failed for getDeviceConfiguration", e);
            throw new RuntimeException("gRPC call failed for getDeviceConfiguration", e);
        }
    }

    public void shutdown() {
        logger.info("Shutting down the gNMI service.");  // <-- Log the shutdown process
        channel.shutdown();
    }
}
