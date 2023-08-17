package me.namila.food_ordering.domain.application.ports.output.message.publisher.payment;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.domain.core.event.OrderPaidEvent;

/**
 * The interface Order paid payment request message publisher.
 */
public interface OrderPaidPaymentRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
