package me.namila.food_ordering.order.domain.core.service;

import java.util.List;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.entity.Order;
import me.namila.food_ordering.order.domain.core.entity.Restaurant;
import me.namila.food_ordering.order.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.order.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.order.domain.core.event.OrderPaidEvent;

/**
 * The interface Order domain service.
 */
public interface OrderDomainService {

  /**
   * Validate and initiate order order created event.
   *
   * @param order the order
   * @param restaurant the restaurant
   * @param orderCreatedEventDomainEventPublisher the order created event domain event publisher
   * @return the order created event
   */
  OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant,
                                             DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher);

  /**
   * Pay order order paid event.
   *
   * @param order the order
   * @param orderPaidEventDomainEventPublisher the order paid event domain event publisher
   * @return the order paid event
   */
  OrderPaidEvent payOrder(Order order,
                          DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

  /**
   * Approve order.
   *
   * @param order the order
   */
  void approveOrder(Order order);

  /**
   * Cancel order payment order cancelled event.
   *
   * @param order the order
   * @param failureMessages the failure messages
   * @param orderCancelledEventDomainEventPublisher the order cancelled event domain event publisher
   * @return the order cancelled event
   */
  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages,
                                         DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher);

  /**
   * Cancel order.
   *
   * @param order the order
   * @param failureMessages the failure messages
   */
  void cancelOrder(Order order, List<String> failureMessages);
}
