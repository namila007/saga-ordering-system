package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.entity.Order;


/**
 * The type Order created event.
 */
public class OrderCreatedEvent extends OrderEvent {

  private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

  /**
   * Instantiates a new Order created event.
   *
   * @param order the order
   * @param createdAt the created at
   * @param orderCreatedEventDomainEventPublisher the order created event domain event publisher
   */
  public OrderCreatedEvent(Order order, ZonedDateTime createdAt,
                           DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
    super(order, createdAt);
    this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderCreatedEventDomainEventPublisher.publish(this);
  }
}
