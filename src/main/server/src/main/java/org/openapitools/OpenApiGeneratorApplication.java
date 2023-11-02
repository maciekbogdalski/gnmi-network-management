package org.openapitools;

import com.ericsson.networkdevice.NorthboundComponentApplication;
import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Entry point for the Spring Boot application.
 * This class sets up the initial configurations, component scans, and bean definitions.
 */
@SpringBootApplication
@Import(NorthboundComponentApplication.class)
@ComponentScan(
        basePackages = {"org.openapitools", "org.openapitools.api" , "org.openapitools.configuration"}
)
public class OpenApiGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
    }

    /**
     * Bean definition for JsonNullableModule to handle JSON fields that can be null.
     */
    @Bean(name = "org.openapitools.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
