package me.namila.food_ordering.payment.domain.ports.input.message.listener;

import me.namila.food_ordering.payment.domain.dto.PaymentRequest;

/**
 * The interface Payment request message listener.
 */
public interface PaymentRequestMessageListener {
  /**
   * Complete payment.
   *
   * @param paymentRequest the payment request
   */
  void completePayment(PaymentRequest paymentRequest);

  /**
   * Cancel payment.
   *
   * @param paymentRequest the payment request
   */
  void cancelPayment(PaymentRequest paymentRequest);
}
