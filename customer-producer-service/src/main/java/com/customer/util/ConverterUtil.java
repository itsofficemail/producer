package com.customer.util;

import com.customer.dto.CustomerDTO;
import com.customer.model.CustomerRequest;
import com.customer.model.converter.CustomerDTOConverter;
import com.customer.model.converter.CustomerRequestConverter;

public class ConverterUtil {

  private static final CustomerRequestConverter CUSTOMER_REQUEST_CONVERTER =
      new CustomerRequestConverter();
  private static final CustomerDTOConverter CUSTOMER_DTO_CONVERTER = new CustomerDTOConverter();

  private ConverterUtil() {}

  public static CustomerRequest mask(CustomerRequest customerRequest) {
    return CUSTOMER_REQUEST_CONVERTER.convert(customerRequest);
  }

  public static CustomerDTO toDTO(CustomerRequest customerRequest) {
    return CUSTOMER_DTO_CONVERTER.convert(customerRequest);
  }
}
