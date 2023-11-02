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
 * ConfigurationPostRequest
 */

@JsonTypeName("_configuration_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
public class ConfigurationPostRequest {

  @NotNull
  private String deviceId;

  @NotNull
  @NotEmpty
  private String _configuration;

  public ConfigurationPostRequest deviceId(String deviceId) {
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

  public ConfigurationPostRequest _configuration(String _configuration) {
    this._configuration = _configuration;
    return this;
  }

  /**
   * Get _configuration
   * @return _configuration
  */
  
  @Schema(name = "configuration", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("configuration")
  public String getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(String _configuration) {
    this._configuration = _configuration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurationPostRequest configurationPostRequest = (ConfigurationPostRequest) o;
    return Objects.equals(this.deviceId, configurationPostRequest.deviceId) &&
        Objects.equals(this._configuration, configurationPostRequest._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deviceId, _configuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurationPostRequest {\n");
    sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
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

