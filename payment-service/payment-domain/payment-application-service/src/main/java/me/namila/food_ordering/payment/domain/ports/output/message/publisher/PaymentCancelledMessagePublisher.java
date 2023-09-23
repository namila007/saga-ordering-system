package me.namila.food_ordering.payment.domain.ports.output.message.publisher;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.event.PaymentCancelledEvent;

/**
 * The interface Payment cancelled message publisher.
 */
public interface PaymentCancelledMessagePublisher
        extends DomainEventPublisher<PaymentCancelledEvent> {
}
