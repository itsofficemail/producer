package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("dev")
public class CoustomerProducerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoustomerProducerServiceApplication.class, args);
  }
}
