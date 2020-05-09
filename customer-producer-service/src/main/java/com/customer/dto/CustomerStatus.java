package com.customer.dto;

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
  public String toString() {
    return String.valueOf(value);
  }

  public static CustomerStatus fromValue(String text) {
    for (CustomerStatus b : CustomerStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
