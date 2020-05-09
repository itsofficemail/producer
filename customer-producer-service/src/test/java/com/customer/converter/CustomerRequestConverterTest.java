package com.customer.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.customer.constants.AppConstants;
import com.customer.model.AddressRequest;
import com.customer.model.CustomerRequest;
import com.customer.model.CustomerStatus;
import com.customer.model.converter.CustomerRequestConverter;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRequestConverterTest {

  @Spy private CustomerRequestConverter customerRequestConverter;

  private CustomerRequest customerRequest = null;

  @Before
  public void setupData() {

    AddressRequest address =
        new AddressRequest()
            .addressLine1("Hyderabad")
            .addressLine2("Telangana")
            .street("Street")
            .postalCode("50937");

    customerRequest =
        new CustomerRequest()
            .customerNumber("C000000001")
            .firstName("MaheshReddy")
            .lastName("SuramSuram")
            .birthDate("2000-01-01")
            .country("India")
            .countryCode("IN")
            .mobileNumber("9999999999")
            .email("mahesh@suram.com")
            .customerStatus(CustomerStatus.OPEN)
            .address(address);
  }

  @Test
  public void testConvertion() {

    CustomerRequest customerMaskedRequest = customerRequestConverter.convert(customerRequest);

    assertNotEquals(customerRequest, customerMaskedRequest);
    assertEquals(customerRequest.getCustomerStatus(), customerMaskedRequest.getCustomerStatus());
    assertEquals(customerRequest.getAddress(), customerMaskedRequest.getAddress());

    assertThat(
        customerRequest.getCustomerNumber().replaceAll(AppConstants.EXCEPT_LAST_4, "*"),
        is(customerMaskedRequest.getCustomerNumber()));

    assertThat(
        customerRequest.getBirthDate().replaceAll(AppConstants.EXCEPT_LAST_4, "*"),
        is(customerMaskedRequest.getBirthDate()));

    assertThat(
        customerRequest.getMobileNumber().replaceAll(AppConstants.EXCEPT_LAST_4, "*"),
        is(customerMaskedRequest.getMobileNumber()));

    assertThat(
        customerRequest.getEmail().replaceAll(AppConstants.EMAIL_MASK_REGEX, "*"),
        is(customerMaskedRequest.getEmail()));

    assertThat(customerRequest.getFirstName(), is(customerMaskedRequest.getFirstName()));
    assertThat(customerRequest.getLastName(), is(customerMaskedRequest.getLastName()));
    assertThat(customerRequest.getCountry(), is(customerMaskedRequest.getCountry()));
    assertThat(customerRequest.getCountryCode(), is(customerMaskedRequest.getCountryCode()));
  }
}
