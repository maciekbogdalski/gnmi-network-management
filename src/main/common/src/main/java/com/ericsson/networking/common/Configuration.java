package com.ericsson.networking.common;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private Map<String, String> configEntries;

    // Constructor that accepts a Map to initialize the configuration
    public Configuration(Map<String, String> configEntries) {
        this.configEntries = new HashMap<>(configEntries); // Create a copy of the map
    }

    // Copy constructor for deep copy
    public Configuration(Configuration other) {
        this.configEntries = new HashMap<>(other.configEntries);
    }

    // Default constructor if needed
    public Configuration() {
        this.configEntries = new HashMap<>();
    }

    // Getter for the configuration entries
    public Map<String, String> getConfigEntries() {
        return configEntries;
    }

    // Setter for the configuration entries
    public void setConfigEntries(Map<String, String> configEntries) {
        this.configEntries = new HashMap<>(configEntries); // Create a copy of the map
    }

    // Method to get a specific configuration value
    public String getConfigValue(String key) {
        return configEntries.get(key);
    }

    // Method to set a specific configuration value
    public void setConfigValue(String key, String value) {
        configEntries.put(key, value);
    }

    // Method to remove a specific configuration value
    public void removeConfigValue(String key) {
        configEntries.remove(key);
    }

    // Method to clear all configuration entries
    public void clearConfiguration() {
        configEntries.clear();
    }

    // Method to merge another Configuration into this one
    public void mergeConfiguration(Configuration other) {
        this.configEntries.putAll(other.getConfigEntries());
    }

    // ToString method to return the configuration as a String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        configEntries.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
        return sb.toString();
    }
}
