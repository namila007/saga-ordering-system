package me.namila.food_ordering.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Customer service application.
 */
@SpringBootApplication(scanBasePackages = "me.namila.food_ordering")
public class CustomerServiceApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceApplication.class);
  }
}
