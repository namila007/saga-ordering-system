package me.namila.food_ordering.payment.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The interface Payment repository.
 */
public interface PaymentRepository {
  /**
   * Save payment.
   *
   * @param payment the payment
   * @return the payment
   */
  Payment save(Payment payment);

  /**
   * Find by order id optional.
   *
   * @param orderId the order id
   * @return the optional
   */
  Optional<Payment> findByOrderId(UUID orderId);

}
