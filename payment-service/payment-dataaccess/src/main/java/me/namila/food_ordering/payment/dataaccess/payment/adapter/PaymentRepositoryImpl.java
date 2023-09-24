package me.namila.food_ordering.payment.dataaccess.payment.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.payment.dataaccess.payment.mapper.PaymentDataAccessMapper;
import me.namila.food_ordering.payment.dataaccess.payment.repository.PaymentJpaRepository;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.ports.output.repository.PaymentRepository;

/**
 * The type Payment repository.
 */
@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;
  private final PaymentDataAccessMapper paymentDataAccessMapper;

  /**
   * Instantiates a new Payment repository.
   *
   * @param paymentJpaRepository    the payment jpa repository
   * @param paymentDataAccessMapper the payment data access mapper
   */
  public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository,
                               PaymentDataAccessMapper paymentDataAccessMapper) {
    this.paymentJpaRepository = paymentJpaRepository;
    this.paymentDataAccessMapper = paymentDataAccessMapper;
  }

  @Override
  public Payment save(Payment payment) {
    return paymentDataAccessMapper.paymentEntityToPayment(
            paymentJpaRepository.save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
  }

  @Override
  public Optional<Payment> findByOrderId(UUID orderId) {
    return paymentJpaRepository.findByOrderId(orderId)
            .map(paymentDataAccessMapper::paymentEntityToPayment);
  }
}
