package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.CapabilityResponseDTO;
import com.github.gnmi.proto.CapabilityResponse;
import com.github.gnmi.proto.ModelData;
import com.github.gnmi.proto.Encoding;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to transform gNMI responses into DTOs.
 * It provides methods to convert gNMI protocol messages into a format suitable for use in higher layers of the application.
 */
public class GnmiTransformer {

    /**
     * Transforms a CapabilityResponse from gNMI into a CapabilityResponseDTO.
     * Extracts and converts relevant information like supported models and encodings from the gNMI response.
     *
     * @param response The gNMI CapabilityResponse to be transformed.
     * @return A CapabilityResponseDTO containing the extracted information from the gNMI response.
     */
    public static CapabilityResponseDTO transformCapabilityResponse(CapabilityResponse response) {
        CapabilityResponseDTO dto = new CapabilityResponseDTO();

        // Set the GNMI version from the response into the DTO.
        dto.setGNMIVersion(response.getGNMIVersion());

        // Transform ModelData list to a list of strings (e.g., model names)
        List<String> modelNames = response.getSupportedModelsList().stream()
                .map(ModelData::getName)  // Assuming you want the name from ModelData
                .collect(Collectors.toList());
        dto.setSupportedModels(modelNames);

        // Transform Encoding list to a list of strings
        List<String> encodings = response.getSupportedEncodingsList().stream()
                .map(Encoding::name)  // Convert the enum to its string representation
                .collect(Collectors.toList());
        dto.setSupportedEncodings(encodings);

        return dto;
    }
}
