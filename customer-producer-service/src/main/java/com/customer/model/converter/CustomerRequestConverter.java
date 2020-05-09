package com.customer.model.converter;

import com.customer.constants.AppConstants;
import com.customer.model.CustomerRequest;

public class CustomerRequestConverter implements Converter<CustomerRequest, CustomerRequest> {

  private static final String REPLACE_WITH = "*";

  @Override
  public CustomerRequest convert(CustomerRequest customerRequest) {

    CustomerRequest customer = new CustomerRequest();

    customer.setCustomerNumber(
        customerRequest.getCustomerNumber().replaceAll(AppConstants.EXCEPT_LAST_4, REPLACE_WITH));
    customer.setFirstName(customerRequest.getFirstName());
    customer.setLastName(customerRequest.getLastName());
    customer.setBirthDate(
        customerRequest.getBirthDate().replaceAll(AppConstants.EXCEPT_LAST_4, REPLACE_WITH));
    customer.setCountry(customerRequest.getCountry());
    customer.setCountryCode(customerRequest.getCountryCode());
    customer.setMobileNumber(
        customerRequest.getMobileNumber().replaceAll(AppConstants.EXCEPT_LAST_4, REPLACE_WITH));
    customer.setEmail(
        customerRequest.getEmail().replaceAll(AppConstants.EMAIL_MASK_REGEX, REPLACE_WITH));
    customer.setCustomerStatus(customerRequest.getCustomerStatus());
    customer.setAddress(customerRequest.getAddress());

    return customer;
  }
}
