package me.namila.food_ordering.payment.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.PaymentOrderStatus;

/**
 * The type Payment request.
 */
@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
  private String id;
  private String sagaId;
  private String orderId;
  private String customerId;
  private BigDecimal price;
  private Instant createdAt;
  private PaymentOrderStatus paymentOrderStatus;

  /**
   * Sets payment order status.
   *
   * @param paymentOrderStatus the payment order status
   */
  public void setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
    this.paymentOrderStatus = paymentOrderStatus;
  }
}
