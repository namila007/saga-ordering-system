package me.namila.food_ordering.payment.dataaccess.payment.mapper;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.payment.dataaccess.payment.entity.PaymentEntity;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.valueobject.PaymentId;

/**
 * The type Payment data access mapper.
 */
@Component
public class PaymentDataAccessMapper {

  /**
   * Payment to payment entity payment entity.
   *
   * @param payment the payment
   * @return the payment entity
   */
  public PaymentEntity paymentToPaymentEntity(Payment payment) {
    return PaymentEntity.builder().id(payment.getId().getBaseId())
            .customerId(payment.getCustomerId().getBaseId()).orderId(payment.getOrderId().getBaseId())
            .price(payment.getPrice().getAmount()).status(payment.getPaymentStatus())
            .createdAt(payment.getCreatedAt()).build();
  }

  /**
   * Payment entity to payment payment.
   *
   * @param paymentEntity the payment entity
   * @return the payment
   */
  public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
    return Payment.Builder.builder().paymentId(new PaymentId(paymentEntity.getId()))
            .customerId(new CustomerId(paymentEntity.getCustomerId()))
            .orderId(new OrderId(paymentEntity.getOrderId())).price(new Money(paymentEntity.getPrice()))
            .createdAt(paymentEntity.getCreatedAt()).build();
  }

}
