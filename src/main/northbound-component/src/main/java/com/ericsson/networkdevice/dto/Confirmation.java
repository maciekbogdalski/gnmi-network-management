package com.ericsson.networkdevice.dto;

public class Confirmation {
    private String message;

    public Confirmation() {}

    public Confirmation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
