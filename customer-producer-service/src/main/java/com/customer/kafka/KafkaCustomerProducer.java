package com.customer.kafka;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.customer.dto.CustomerDTO;
import com.customer.exception.GeneralException;

@Component
public class KafkaCustomerProducer {

  @Autowired private KafkaTemplate<String, CustomerDTO> kafkaTemplate;

  private static final String CUSTOMER_TOPIC = "customer-topic";

  public void send(CustomerDTO customerDTO) {

    try {

      ListenableFuture<SendResult<String, CustomerDTO>> response =
          kafkaTemplate.send(CUSTOMER_TOPIC, customerDTO);

      response.get();

    } catch (InterruptedException | ExecutionException e) {
      throw new GeneralException(e.getClass().getName());
    }
  }
}
