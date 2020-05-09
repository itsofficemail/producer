package com.customer.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.kafka.KafkaCustomerProducer;
import com.customer.model.CustomerRequest;
import com.customer.model.RestAPIResponse;
import com.customer.util.ConverterUtil;
import com.customer.util.JsonUtil;

@RestController
@RequestMapping("/user")
public class CustomerRestController {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerRestController.class);

  @Autowired private KafkaCustomerProducer userProducer;

  @GetMapping("/authenticate")
  public Principal user(Principal user) {
    return user;
  }

  @PostMapping
  public ResponseEntity<RestAPIResponse> addUser(
      @Valid @RequestBody CustomerRequest customerRequest) {

    LOG.info(
        "Sending customer data to kafka {}", JsonUtil.objToString(ConverterUtil.mask(customerRequest)));

    userProducer.send(ConverterUtil.toDTO(customerRequest));

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new RestAPIResponse().status("success").message("customer data sent to kafka"));
  }
}
