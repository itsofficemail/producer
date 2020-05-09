package com.customer.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.customer.dto.CustomerDTO;
import com.customer.model.AddressRequest;
import com.customer.model.CustomerRequest;
import com.customer.model.CustomerStatus;
import com.customer.model.converter.CustomerDTOConverter;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDTOConverterTest {

  @Spy private CustomerDTOConverter customerDTOConverter;

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

    CustomerDTO customerDTO = customerDTOConverter.convert(customerRequest);

    assertNotNull(customerDTO);
    assertNotNull(customerDTO.getAddress());
    assertThat(customerRequest.getCustomerNumber(), is(customerDTO.getCustomerNumber()));
    assertThat(customerRequest.getFirstName(), is(customerDTO.getFirstName()));
    assertThat(customerRequest.getLastName(), is(customerDTO.getLastName()));
    assertThat(customerRequest.getBirthDate(), is(customerDTO.getBirthDate()));
    assertThat(customerRequest.getCountry(), is(customerDTO.getCountry()));
    assertThat(customerRequest.getCountryCode(), is(customerDTO.getCountryCode()));
    assertThat(customerRequest.getMobileNumber(), is(customerDTO.getMobileNumber()));
    assertThat(customerRequest.getEmail(), is(customerDTO.getEmail()));
    assertThat(
        customerRequest.getCustomerStatus().toString(),
        is(customerDTO.getCustomerStatus().toString()));

    assertThat(
        customerRequest.getAddress().getAddressLine1(),
        is(customerDTO.getAddress().getAddressLine1()));
    assertThat(
        customerRequest.getAddress().getAddressLine2(),
        is(customerDTO.getAddress().getAddressLine2()));
    assertThat(customerRequest.getAddress().getStreet(), is(customerDTO.getAddress().getStreet()));
    assertThat(
        customerRequest.getAddress().getPostalCode(), is(customerDTO.getAddress().getPostalCode()));
  }
}
