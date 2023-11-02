package com.ericsson.networkdevice.dto;

import java.util.List;

public class CapabilityResponseDTO {

    private String gNMIVersion;
    private List<String> supportedModels;
    private List<String> supportedEncodings;

    // Constructors
    public CapabilityResponseDTO() {
    }

    public CapabilityResponseDTO(String gNMIVersion, List<String> supportedModels, List<String> supportedEncodings) {
        this.gNMIVersion = gNMIVersion;
        this.supportedModels = supportedModels;
        this.supportedEncodings = supportedEncodings;
    }

    // Getters and Setters
    public String getGNMIVersion() {
        return gNMIVersion;
    }

    public void setGNMIVersion(String gNMIVersion) {
        this.gNMIVersion = gNMIVersion;
    }

    public List<String> getSupportedModels() {
        return supportedModels;
    }

    public void setSupportedModels(List<String> supportedModels) {
        this.supportedModels = supportedModels;
    }

    public List<String> getSupportedEncodings() {
        return supportedEncodings;
    }

    public void setSupportedEncodings(List<String> supportedEncodings) {
        this.supportedEncodings = supportedEncodings;
    }

    // Optionally, you can also override the `toString` method for better logging/debugging
    @Override
    public String toString() {
        return "CapabilityResponseDTO{" +
                "gNMIVersion='" + gNMIVersion + '\'' +
                ", supportedModels=" + supportedModels +
                ", supportedEncodings=" + supportedEncodings +
                '}';
    }
}
