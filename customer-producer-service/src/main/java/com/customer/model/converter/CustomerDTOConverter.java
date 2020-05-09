package com.customer.model.converter;

import com.customer.dto.AddressDTO;
import com.customer.dto.CustomerDTO;
import com.customer.dto.CustomerStatus;
import com.customer.model.CustomerRequest;

public class CustomerDTOConverter implements Converter<CustomerRequest, CustomerDTO> {

  @Override
  public CustomerDTO convert(CustomerRequest customerRequest) {

    CustomerDTO customerDTO = new CustomerDTO();

    customerDTO.setCustomerNumber(customerRequest.getCustomerNumber());
    customerDTO.setFirstName(customerRequest.getFirstName());
    customerDTO.setLastName(customerRequest.getLastName());
    customerDTO.setBirthDate(customerRequest.getBirthDate());
    customerDTO.setCountry(customerRequest.getCountry());
    customerDTO.setCountryCode(customerRequest.getCountryCode());
    customerDTO.setMobileNumber(customerRequest.getMobileNumber());
    customerDTO.setEmail(customerRequest.getEmail());

    CustomerStatus customerStatus =
        CustomerStatus.fromValue(customerRequest.getCustomerStatus().toString());
    customerDTO.setCustomerStatus(customerStatus);

    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setAddressLine1(customerRequest.getAddress().getAddressLine1());
    addressDTO.setAddressLine2(customerRequest.getAddress().getAddressLine2());
    addressDTO.setStreet(customerRequest.getAddress().getStreet());
    addressDTO.setPostalCode(customerRequest.getAddress().getPostalCode());
    customerDTO.setAddress(addressDTO);

    return customerDTO;
  }
}
