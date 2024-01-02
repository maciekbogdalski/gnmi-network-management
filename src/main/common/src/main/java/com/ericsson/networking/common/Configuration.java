package com.ericsson.networking.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the configuration of a network device.
 * Stores configuration entries as key-value pairs.
 */
public class Configuration {

    private Map<String, String> configEntries;

    /**
     * Constructs a Configuration object with provided configuration entries.
     * @param configEntries A map containing the initial configuration entries.
     */
    public Configuration(Map<String, String> configEntries) {
        this.configEntries = new HashMap<>(configEntries); // Create a copy of the map
    }



    /**
     * Copy constructor for deep copying another Configuration object.
     * @param other The Configuration object to be copied.
     */
    public Configuration(Configuration other) {
        this.configEntries = new HashMap<>(other.configEntries);
    }

    /**
     * Default constructor initializing an empty configuration.
     */
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

    /**
     * Retrieves a specific configuration value.
     * @param key The key for the configuration entry.
     * @return The value associated with the key, or null if not found.
     */
    public String getConfigValue(String key) {
        return configEntries.get(key);
    }

    /**
     * Sets a specific configuration value.
     * @param key The key for the configuration entry.
     * @param value The value to be associated with the key.
     */ // Method to set a specific configuration value
    public void setConfigValue(String key, String value) {
        configEntries.put(key, value);
    }

    /**
     * Removes a specific configuration entry.
     * @param key The key of the configuration entry to be removed.
     */
    public void removeConfigValue(String key) {
        configEntries.remove(key);
    }

    /**
     * Clears all configuration entries.
     */
    public void clearConfiguration() {
        configEntries.clear();
    }

    /**
     * Merges another Configuration object into this one.
     * @param other The Configuration object to be merged.
     */
    public void mergeConfiguration(Configuration other) {
        this.configEntries.putAll(other.getConfigEntries());
    }

    /**
     * Converts the configuration entries to a String representation.
     * @return A string representation of all configuration entries.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        configEntries.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
        return sb.toString();
    }
}
