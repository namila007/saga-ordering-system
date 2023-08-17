package me.namila.food_ordering.domain.core.service;

import java.util.List;

import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.Restaurant;
import me.namila.food_ordering.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.domain.core.event.OrderPaidEvent;

/**
 * The interface Order domain service.
 */
public interface OrderDomainService {

  /**
   * Validate and initiate order order created event.
   *
   * @param order the order
   * @param restaurant the restaurant
   * @return the order created event
   */
  OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

  /**
   * Pay order order paid event.
   *
   * @param order the order
   * @return the order paid event
   */
  OrderPaidEvent payOrder(Order order);

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
   * @return the order cancelled event
   */
  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

  /**
   * Cancel order.
   *
   * @param order the order
   * @param failureMessages the failure messages
   */
  void cancelOrder(Order order, List<String> failureMessages);
}
