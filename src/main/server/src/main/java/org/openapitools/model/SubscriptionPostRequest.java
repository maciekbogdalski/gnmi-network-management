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
 * SubscriptionPostRequest
 */

@JsonTypeName("_subscription_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
public class SubscriptionPostRequest {

  @NotNull
  private String deviceId;

  @NotNull
  @NotEmpty
  private String subscriptionType;

  private Integer pollingInterval;

  public SubscriptionPostRequest deviceId(String deviceId) {
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

  public SubscriptionPostRequest subscriptionType(String subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Get subscriptionType
   * @return subscriptionType
  */
  
  @Schema(name = "subscriptionType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subscriptionType")
  public String getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(String subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public SubscriptionPostRequest pollingInterval(Integer pollingInterval) {
    this.pollingInterval = pollingInterval;
    return this;
  }

  /**
   * Get pollingInterval
   * @return pollingInterval
  */
  
  @Schema(name = "pollingInterval", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pollingInterval")
  public Integer getPollingInterval() {
    return pollingInterval;
  }

  public void setPollingInterval(Integer pollingInterval) {
    this.pollingInterval = pollingInterval;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionPostRequest subscriptionPostRequest = (SubscriptionPostRequest) o;
    return Objects.equals(this.deviceId, subscriptionPostRequest.deviceId) &&
        Objects.equals(this.subscriptionType, subscriptionPostRequest.subscriptionType) &&
        Objects.equals(this.pollingInterval, subscriptionPostRequest.pollingInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deviceId, subscriptionType, pollingInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionPostRequest {\n");
    sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    pollingInterval: ").append(toIndentedString(pollingInterval)).append("\n");
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

