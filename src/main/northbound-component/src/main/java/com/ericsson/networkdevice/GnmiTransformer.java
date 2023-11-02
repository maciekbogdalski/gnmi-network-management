package com.ericsson.networkdevice;

import com.ericsson.networkdevice.dto.CapabilityResponseDTO;
import com.github.gnmi.proto.CapabilityResponse;
import com.github.gnmi.proto.ModelData;
import com.github.gnmi.proto.Encoding;

import java.util.List;
import java.util.stream.Collectors;

public class GnmiTransformer {

    public static CapabilityResponseDTO transformCapabilityResponse(CapabilityResponse response) {
        CapabilityResponseDTO dto = new CapabilityResponseDTO();

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
