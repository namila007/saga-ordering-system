package me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.event.OrderCreatedEvent;

/**
 * The interface Order created payment request message publisher.
 */
public interface OrderCreatedPaymentRequestMessagePublisher
    extends DomainEventPublisher<OrderCreatedEvent> {
}
