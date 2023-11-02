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
 * ConfigurationGet200Response
 */

@JsonTypeName("_configuration_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
public class ConfigurationGet200Response {

  private String _configuration;

  public ConfigurationGet200Response _configuration(String _configuration) {
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
    ConfigurationGet200Response configurationGet200Response = (ConfigurationGet200Response) o;
    return Objects.equals(this._configuration, configurationGet200Response._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_configuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurationGet200Response {\n");
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

