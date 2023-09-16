package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order paid event.
 */
public class OrderPaidEvent extends OrderEvent {
  /**
   * Instantiates a new Order paid event.
   *
   * @param order     the order
   * @param createdAt the created at
   */
  public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
