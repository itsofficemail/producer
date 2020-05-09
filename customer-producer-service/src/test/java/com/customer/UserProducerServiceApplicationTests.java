package com.customer;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.customer.model.AddressRequest;
import com.customer.model.CustomerRequest;
import com.customer.model.CustomerStatus;
import com.customer.util.JsonUtil;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserProducerServiceApplicationTests {

  @Autowired private MockMvc mockMvc;

  // @Mock private KafkaCustomerProducer customerProducer;
  // @InjectMocks private CustomerRestController customerRestController;

  private CustomerRequest customerRequest = null;
  // private ResponseEntity<RestAPIResponse> apiResponse = null;

  @BeforeEach
  public void setupData() {

    AddressRequest address =
        new AddressRequest()
            .addressLine1("Hyderabad")
            .addressLine2("Telangana")
            .addressLine2("Street")
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

    /*apiResponse =
    ResponseEntity.status(HttpStatus.CREATED)
        .body(new RestAPIResponse().status("success").message("customer data sent to kafka"));*/
  }

  @Test
  public void accessProtected() throws Exception {
    this.mockMvc.perform(get("/")).andExpect(status().isUnauthorized());
  }
  /*
  @Test
  public void loginUserWithWrongCredentials() throws Exception {

    this.mockMvc
        .perform(
            post("/oauth/token").params(grantTypeHeaderParams()).with(httpBasic("tom", "jerry")))
        .andExpect(status().isUnauthorized());
  }*/

  @Test
  public void loginUserWithCorrectCredentials() throws Exception {

    this.mockMvc
        .perform(
            post("/oauth/token")
                .params(grantTypeHeaderParams())
                .with(httpBasic("mobile-client-id", "mobile-client-secret")))
        .andExpect(status().isOk());
  }

  @Test
  public void postUserWitoutAccessToken() throws Exception {

    mockMvc
        .perform(
            post("/user")
                .header("Authorization", "Bearer ")
                .content(JsonUtil.objToString(customerRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void postUserWithAccessToken() throws Exception {

    // doNothing().when(customerProducer).send(ConverterUtil.toDTO(customerRequest));
    // when(customerRestController.addUser(customerRequest)).thenReturn(apiResponse);

    String accessToken = getAccessToken();

    mockMvc
        .perform(
            post("/user")
                .header("Authorization", "Bearer " + accessToken)
                .content(JsonUtil.objToString(customerRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status").value("success"));
  }

  private String getAccessToken() throws Exception {

    MvcResult result =
        this.mockMvc
            .perform(
                post("/oauth/token")
                    .params(grantTypeHeaderParams())
                    .with(httpBasic("mobile-client-id", "mobile-client-secret")))
            .andExpect(status().isOk())
            .andReturn();

    String response = result.getResponse().getContentAsString();
    String accessToken = JsonPath.parse(response).read("$.access_token");

    return accessToken;
  }

  private MultiValueMap<String, String> grantTypeHeaderParams() {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "client_credentials");

    return params;
  }
}
