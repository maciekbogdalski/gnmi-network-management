package com.ericsson.networkdevice.dto;

public class SetDataDTO {
    private String path;
    private String data;

    // Default constructor for JSON deserialization
    public SetDataDTO() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
