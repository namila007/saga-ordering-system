package me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.event.OrderPaidEvent;

/**
 * The interface Order paid payment request message publisher.
 */
public interface OrderPaidPaymentRequestMessagePublisher
    extends DomainEventPublisher<OrderPaidEvent> {
}
