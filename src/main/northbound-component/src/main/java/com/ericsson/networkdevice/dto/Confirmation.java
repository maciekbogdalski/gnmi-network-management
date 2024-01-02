package com.ericsson.networkdevice.dto;

/**
 * Data Transfer Object for encapsulating confirmation messages.
 * This class is typically used to provide feedback or response messages back to the client.
 */
public class Confirmation {
    private String message;

    /**
     * Default constructor.
     */
    public Confirmation() {}

    /**
     * Constructor with message parameter.
     * @param message The confirmation message to be encapsulated in this DTO.
     */
    public Confirmation(String message) {
        this.message = message;
    }

    /**
     * Gets the confirmation message.
     * @return The confirmation message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the confirmation message.
     * @param message The confirmation message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
