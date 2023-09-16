package me.namila.food_ordering.order.domain.core.event;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.namila.food_ordering.common.event.DomainEvent;
import me.namila.food_ordering.order.domain.core.entity.Order;

/**
 * The type Order event.
 */
@RequiredArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
  private final Order order;
  private final ZonedDateTime createdAt;

}
