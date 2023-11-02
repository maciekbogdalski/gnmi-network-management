/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.ConfigurationGet200Response;
import org.openapitools.model.ConfigurationPostRequest;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Validated
@Tag(name = "configuration", description = "the configuration API")
public interface ConfigurationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /configuration : Get device configuration
     *
     * @param deviceId ID of the device to retrieve configuration for. (required)
     * @return Device configuration retrieved successfully. (status code 200)
     */
    @Operation(
            operationId = "getDeviceConfiguration",
            summary = "Get device configuration",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Device configuration retrieved successfully.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ConfigurationGet200Response.class))
                    })
            }
    )
    @GetMapping(
            value = "/configuration",
            produces = { "application/json" }
    )
    ResponseEntity<ConfigurationGet200Response> getDeviceConfiguration(
            @NotNull @Parameter(name = "deviceId", description = "ID of the device to retrieve configuration for.", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "deviceId", required = true) String deviceId
    );

    /**
     * POST /configuration : Set device configuration
     *
     * @param deviceId ID of the device to set configuration for. (required)
     * @param configuration Configuration data to be set. (required)
     * @return Device configuration set successfully. (status code 200)
     */
    @Operation(
            operationId = "setDeviceConfiguration",
            summary = "Set device configuration",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Device configuration set successfully.")
            }
    )
    @PostMapping(
            value = "/configuration",
            consumes = { "application/json" }
    )
    ResponseEntity<Void> setDeviceConfiguration(
            @NotNull @Parameter(name = "deviceId", description = "ID of the device to set configuration for.", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "deviceId", required = true) String deviceId,
            @Parameter(name = "configuration", description = "Configuration data to be set.", required = true) @Valid @RequestBody String configuration
    );
}
