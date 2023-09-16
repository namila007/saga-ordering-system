package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order created event.
 */
public class OrderCreatedEvent extends OrderEvent {
  /**
   * Instantiates a new Order created event.
   *
   * @param order     the order
   * @param createdAt the created at
   */
  public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
