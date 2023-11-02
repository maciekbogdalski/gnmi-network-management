package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfiguration {

    /**
     * Configuration for the OpenAPI documentation.
     *
     * @return OpenAPI object with the specified API information.
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Northbound API")
                                .description("API for interacting with the Northbound component of the network device management system.")
                                .version("1.0.0")
                );
    }
}
