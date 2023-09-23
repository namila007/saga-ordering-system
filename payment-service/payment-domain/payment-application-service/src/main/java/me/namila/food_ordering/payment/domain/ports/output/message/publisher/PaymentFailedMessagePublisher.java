package me.namila.food_ordering.payment.domain.ports.output.message.publisher;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.event.PaymentFailedEvent;

/**
 * The interface Payment failed message publisher.
 */
public interface PaymentFailedMessagePublisher extends DomainEventPublisher<PaymentFailedEvent> {
}
