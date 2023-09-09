package me.namila.food_ordering.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.namila.food_ordering.domain.core.service.OrderDomainService;
import me.namila.food_ordering.domain.core.service.OrderDomainServiceImpl;

/**
 * The type Bean configuration.
 */
@Configuration
public class BeanConfiguration {

  /**
   * Get order domain service order domain service.
   *
   * @return the order domain service
   */
  @Bean
  public OrderDomainService getOrderDomainService() {
    return new OrderDomainServiceImpl();
  }
}
