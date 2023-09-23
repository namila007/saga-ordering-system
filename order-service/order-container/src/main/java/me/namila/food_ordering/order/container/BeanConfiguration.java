package me.namila.food_ordering.order.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.order.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.order.domain.core.event.OrderPaidEvent;
import me.namila.food_ordering.order.domain.core.service.OrderDomainService;
import me.namila.food_ordering.order.domain.core.service.OrderDomainServiceImpl;

/**
 * The type Bean configuration.
 */
@Configuration
public class BeanConfiguration {

  private DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;
  private DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;
  private DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher;

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
