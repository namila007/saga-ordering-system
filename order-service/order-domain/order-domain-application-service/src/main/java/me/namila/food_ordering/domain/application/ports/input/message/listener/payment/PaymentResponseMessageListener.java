package me.namila.food_ordering.domain.application.ports.input.message.listener.payment;

import me.namila.food_ordering.domain.application.dto.message.PaymentResponse;

/**
 * The interface Payment response message listener.
 */
public interface PaymentResponseMessageListener {

  /**
   * Payment completed.
   *
   * @param paymentResponse the payment response
   */
  void paymentCompleted(PaymentResponse paymentResponse);

  /**
   * Payment cancelled.
   *
   * @param paymentResponse the payment response
   */
  void paymentCancelled(PaymentResponse paymentResponse);
}
