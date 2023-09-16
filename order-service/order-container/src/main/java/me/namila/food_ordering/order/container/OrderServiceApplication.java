package me.namila.food_ordering.order.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Order service application.
 */
@EnableJpaAuditing
@EntityScan(basePackages = "me.namila.food_ordering.order")
@EnableJpaRepositories(basePackages = "me.namila.food_ordering.order")
@ConfigurationPropertiesScan(
    basePackages = {"me.namila.food_ordering.order", "me.namila.food_ordering.kafka"})
@SpringBootApplication(
    scanBasePackages = {"me.namila.food_ordering.order", "me.namila.food_ordering.kafka"})
public class OrderServiceApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class);
  }
}
