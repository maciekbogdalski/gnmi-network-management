/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.DevicePostRequest;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
@Validated
@Tag(name = "device", description = "the device API")
// ... [Other imports]

public interface DeviceApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(
            operationId = "deviceDelete",
            summary = "Remove a device",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Device removed successfully.")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/device/{address}/{port}"
    )
    ResponseEntity<Void> deviceDelete(
            @Parameter(name = "address", description = "IP address of the device to remove.", required = true, in = ParameterIn.PATH) @PathVariable(value = "address") String address,
            @Parameter(name = "port", description = "Port of the device to remove.", required = true, in = ParameterIn.PATH) @PathVariable(value = "port") int port );

    @Operation(
            operationId = "devicePost",
            summary = "Add a new device",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Device added successfully.")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/device/{address}/{port}",
            consumes = { "application/json" }
    )
    ResponseEntity<Void> devicePost(
            @Parameter(name = "address", description = "IP address of the device to add.", required = true, in = ParameterIn.PATH) @PathVariable(value = "address") String address,
            @Parameter(name = "port", description = "Port of the device to add.", required = true, in = ParameterIn.PATH) @PathVariable(value = "port") int port
    );
}
