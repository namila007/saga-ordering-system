package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order cancelled event.
 */
public class OrderCancelledEvent extends OrderEvent {
  /**
   * Instantiates a new Order cancelled event.
   *
   * @param order     the order
   * @param createdAt the created at
   */
  public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
