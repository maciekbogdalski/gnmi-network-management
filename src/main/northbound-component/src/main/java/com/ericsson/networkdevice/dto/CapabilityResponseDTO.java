package com.ericsson.networkdevice.dto;

import java.util.List;

/**
 * Data Transfer Object for gNMI capability response.
 * This class stores information about a device's gNMI version, supported models, and supported encodings.
 */
public class CapabilityResponseDTO {

    private String gNMIVersion;
    private List<String> supportedModels;
    private List<String> supportedEncodings;

    /**
     * Default constructor for CapabilityResponseDTO.
     */
    public CapabilityResponseDTO() {
    }

    /**
     * Constructor with parameters to initialize the DTO.
     * @param gNMIVersion The gNMI version of the device.
     * @param supportedModels A list of supported models by the device.
     * @param supportedEncodings A list of supported encodings by the device.
     */
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

    /**
     * Returns a string representation of the CapabilityResponseDTO.
     * Useful for logging and debugging purposes.
     * @return A string representation of the DTO.
     */
    @Override
    public String toString() {
        return "CapabilityResponseDTO{" +
                "gNMIVersion='" + gNMIVersion + '\'' +
                ", supportedModels=" + supportedModels +
                ", supportedEncodings=" + supportedEncodings +
                '}';
    }
}
