package com.customer.dto;

import java.util.Objects;

public class CustomerDTO {
	
  private String customerNumber = null;
  private String firstName = null;
  private String lastName = null;
  private String birthDate = null;
  private String country = null;
  private String countryCode = null;
  private String mobileNumber = null;
  private String email = null;
  private CustomerStatus customerStatus = null;
  private AddressDTO address = null;

  public CustomerDTO customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public CustomerDTO firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CustomerDTO lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CustomerDTO birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public CustomerDTO country(String country) {
    this.country = country;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CustomerDTO countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public CustomerDTO mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public CustomerDTO email(String email) {
    this.email = email;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerDTO customerStatus(CustomerStatus customerStatus) {
    this.customerStatus = customerStatus;
    return this;
  }

  public CustomerStatus getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(CustomerStatus customerStatus) {
    this.customerStatus = customerStatus;
  }

  public CustomerDTO address(AddressDTO address) {
    this.address = address;
    return this;
  }

  public AddressDTO getAddress() {
    return address;
  }

  public void setAddress(AddressDTO address) {
    this.address = address;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerDTO customerDTO = (CustomerDTO) o;
    return Objects.equals(this.customerNumber, customerDTO.customerNumber)
        && Objects.equals(this.firstName, customerDTO.firstName)
        && Objects.equals(this.lastName, customerDTO.lastName)
        && Objects.equals(this.birthDate, customerDTO.birthDate)
        && Objects.equals(this.country, customerDTO.country)
        && Objects.equals(this.countryCode, customerDTO.countryCode)
        && Objects.equals(this.mobileNumber, customerDTO.mobileNumber)
        && Objects.equals(this.email, customerDTO.email)
        && Objects.equals(this.customerStatus, customerDTO.customerStatus)
        && Objects.equals(this.address, customerDTO.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customerNumber,
        firstName,
        lastName,
        birthDate,
        country,
        countryCode,
        mobileNumber,
        email,
        customerStatus,
        address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerDTO {\n");

    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
