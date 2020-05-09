package com.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Deprecated //USE SWAGGER GENERATED CLASS INSTEAD
public class AddressDTOSample implements Serializable {

  /** */
  private static final long serialVersionUID = 7689039097970183441L;

  @NotNull(message = "Addressline 1 is required")
  @NotEmpty(message = "Addressline 1 can not be empty")
  @Size(min=5, max=50, message="Addressline 1 shoulld be min of 5 and max of 50")
  private String addressLine1;
  
  private String addressLine2;
  private String street;
  
  @NotNull(message = "Postalcode is required")
  @NotEmpty(message = "Postalcode can not be empty")
  @Size(min = 5, max = 5, message = "Postalcode should be 5 digits")
  private String postalcode;

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalcode() {
    return postalcode;
  }

  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }
}
