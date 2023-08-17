package me.namila.food_ordering.domain.application.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.PaymentStatus;

/**
 * The type Payment response.
 */
@Getter
@AllArgsConstructor
@Builder
public class PaymentResponse {
  private String id;
  private String sagaId;
  private String orderId;
  private String paymentId;
  private String customerId;
  private BigDecimal price;
  private Instant createdAt;
  private PaymentStatus paymentStatus;
  private List<String> failureMessages;

}
