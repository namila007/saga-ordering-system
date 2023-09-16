package me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.event.OrderCancelledEvent;

/**
 * The interface Order cancelled payment request message publisher.
 */
public interface OrderCancelledPaymentRequestMessagePublisher
    extends DomainEventPublisher<OrderCancelledEvent> {
}
