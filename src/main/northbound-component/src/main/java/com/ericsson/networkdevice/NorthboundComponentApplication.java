package com.ericsson.networkdevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application entry point for the Northbound Component.
 * It initializes and runs the Spring Boot application, scanning specified base packages for Spring components.
 */
@SpringBootApplication(scanBasePackages = {"com.ericsson.networkdevice" , "com.ericsson.networking" , "com.ericsson.southbound"})
public class NorthboundComponentApplication {

    /**
     * Main method to launch the Northbound Component application.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(NorthboundComponentApplication.class, args);

    }
}
