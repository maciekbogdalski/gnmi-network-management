package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DevicePostRequest
 */

@JsonTypeName("_device_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
public class DevicePostRequest {

  @NotNull
  private String deviceId;

  @NotNull
  @NotEmpty
  private String deviceType;

  private String initialConfiguration;

  public DevicePostRequest deviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

  /**
   * Get deviceId
   * @return deviceId
  */
  
  @Schema(name = "deviceId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deviceId")
  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public DevicePostRequest deviceType(String deviceType) {
    this.deviceType = deviceType;
    return this;
  }

  /**
   * Get deviceType
   * @return deviceType
  */
  
  @Schema(name = "deviceType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deviceType")
  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public DevicePostRequest initialConfiguration(String initialConfiguration) {
    this.initialConfiguration = initialConfiguration;
    return this;
  }

  /**
   * Get initialConfiguration
   * @return initialConfiguration
  */
  
  @Schema(name = "initialConfiguration", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("initialConfiguration")
  public String getInitialConfiguration() {
    return initialConfiguration;
  }

  public void setInitialConfiguration(String initialConfiguration) {
    this.initialConfiguration = initialConfiguration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevicePostRequest devicePostRequest = (DevicePostRequest) o;
    return Objects.equals(this.deviceId, devicePostRequest.deviceId) &&
        Objects.equals(this.deviceType, devicePostRequest.deviceType) &&
        Objects.equals(this.initialConfiguration, devicePostRequest.initialConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deviceId, deviceType, initialConfiguration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DevicePostRequest {\n");
    sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
    sb.append("    deviceType: ").append(toIndentedString(deviceType)).append("\n");
    sb.append("    initialConfiguration: ").append(toIndentedString(initialConfiguration)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

