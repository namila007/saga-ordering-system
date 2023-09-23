package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order cancelled event.
 */
public class OrderCancelledEvent extends OrderEvent {
  private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;
  /**
   * Instantiates a new Order cancelled event.
   *
   * @param order                                   the order
   * @param createdAt                               the created at
   * @param orderCancelledEventDomainEventPublisher
   */
  public OrderCancelledEvent(Order order, ZonedDateTime createdAt, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
    super(order, createdAt);
    this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderCancelledEventDomainEventPublisher.publish(this);
  }
}
