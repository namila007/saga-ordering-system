package me.namila.food_ordering.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Order service application.
 */
@EnableConfigurationProperties
@EnableJpaAuditing
@EntityScan(basePackages = "me.namila.food_ordering.dataaccess")
@EnableJpaRepositories(basePackages = "me.namila.food_ordering.dataaccess")
@SpringBootApplication(scanBasePackages = "me.namila.food_ordering")
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
