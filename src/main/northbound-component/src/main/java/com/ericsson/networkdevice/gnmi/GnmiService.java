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

import org.slf4j.Logger;  // <-- Import the Logger class
import org.slf4j.LoggerFactory;  // <-- Import the LoggerFactory class

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Somewhere in your package, define the custom exception:
    public class CapabilitiesFetchException extends RuntimeException {
        public CapabilitiesFetchException(String message, Throwable cause) {
            super(message, cause);
        }
    }




    public void shutdown() {
        logger.info("Shutting down the gNMI service.");  // <-- Log the shutdown process
        channel.shutdown();
    }
}
