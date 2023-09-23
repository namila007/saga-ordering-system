package me.namila.food_ordering.payment.domain.ports.output.message.publisher;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.event.PaymentCompletedEvent;

/**
 * The interface Payment completed message publisher.
 */
public interface PaymentCompletedMessagePublisher
        extends DomainEventPublisher<PaymentCompletedEvent> {
}
