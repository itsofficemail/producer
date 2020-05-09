package com.customer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CustomerStatus
 */
public enum CustomerStatus {
  OPEN("OPEN"),
    CLOSE("CLOSE"),
    SUSPENDED("SUSPENDED"),
    RESTORED("RESTORED");

  private String value;

  CustomerStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CustomerStatus fromValue(String text) {
    for (CustomerStatus b : CustomerStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
