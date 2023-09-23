package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order paid event.
 */
public class OrderPaidEvent extends OrderEvent {
  private final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher;

  /**
   * Instantiates a new Order paid event.
   *
   * @param order the order
   * @param createdAt the created at
   * @param orderPaidEventDomainEventPublisher the order paid event domain event publisher
   */
  public OrderPaidEvent(Order order, ZonedDateTime createdAt,
                        DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
    super(order, createdAt);
    this.orderPaidEventDomainEventPublisher = orderPaidEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderPaidEventDomainEventPublisher.publish(this);
  }
}
