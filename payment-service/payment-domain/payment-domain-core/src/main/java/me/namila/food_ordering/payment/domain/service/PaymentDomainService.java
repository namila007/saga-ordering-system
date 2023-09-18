package me.namila.food_ordering.payment.domain.service;

import java.util.List;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.event.PaymentCancelledEvent;
import me.namila.food_ordering.payment.domain.event.PaymentCompletedEvent;
import me.namila.food_ordering.payment.domain.event.PaymentEvent;
import me.namila.food_ordering.payment.domain.event.PaymentFailedEvent;

/**
 * The interface Payment domain service.
 */
public interface PaymentDomainService {

    /**
     * Validate and initiate payment payment event.
     *
     * @param payment                                   the payment
     * @param creditEntry                               the credit entry
     * @param creditHistories                           the credit histories
     * @param failureMessages                           the failure messages
     * @param paymentCompletedEventDomainEventPublisher the payment completed event domain event
     *                                                  publisher
     * @param paymentFailedEventDomainEventPublisher    the payment failed event domain event publisher
     * @return the payment event
     */
    PaymentEvent validateAndInitiatePayment(Payment payment, CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories, List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
                                            DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    /**
     * Validate and cancel payment payment event.
     *
     * @param payment                                   the payment
     * @param creditEntry                               the credit entry
     * @param creditHistories                           the credit histories
     * @param failureMessages                           the failure messages
     * @param paymentCancelledEventDomainEventPublisher the payment cancelled event domain event
     *                                                  publisher
     * @param paymentFailedEventDomainEventPublisher    the payment failed event domain event publisher
     * @return the payment event
     */
    PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories, List<String> failureMessages,
                                          DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
                                          DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
