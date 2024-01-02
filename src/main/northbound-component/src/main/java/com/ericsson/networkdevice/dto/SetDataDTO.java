package com.ericsson.networkdevice.dto;

/**
 * Data Transfer Object for setting data on a network device.
 * This class encapsulates the data path and the actual data to be set on the device.
 */
public class SetDataDTO {
    private String path; // The GNMI path where the data should be set.
    private String data; // The data to be set at the specified path.

    /**
     * Default constructor, mainly used for JSON deserialization.
     */
    public SetDataDTO() {}

    /**
     * Gets the GNMI path where the data is to be set.
     * @return A string representing the GNMI path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the GNMI path where the data is to be set.
     * @param path A string representing the GNMI path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the data to be set on the network device.
     * @return The data as a string.
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data to be set on the network device.
     * @param data The data as a string.
     */
    public void setData(String data) {
        this.data = data;
    }
}
