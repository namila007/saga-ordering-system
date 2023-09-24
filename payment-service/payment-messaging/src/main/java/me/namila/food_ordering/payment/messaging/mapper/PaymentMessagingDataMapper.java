package me.namila.food_ordering.payment.messaging.mapper;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.food_ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import me.food_ordering.system.kafka.order.avro.model.PaymentStatus;
import me.namila.food_ordering.common.valueobject.PaymentOrderStatus;
import me.namila.food_ordering.payment.domain.dto.PaymentRequest;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.event.PaymentCancelledEvent;
import me.namila.food_ordering.payment.domain.event.PaymentCompletedEvent;
import me.namila.food_ordering.payment.domain.event.PaymentEvent;
import me.namila.food_ordering.payment.domain.event.PaymentFailedEvent;

/**
 * The type Payment messaging data mapper.
 */
@Component
public class PaymentMessagingDataMapper {
  /**
   * Payment completed event to payment response avro model payment response avro model.
   *
   * @param paymentCompletedEvent the payment completed event
   * @return the payment response avro model
   */
  public PaymentResponseAvroModel paymentCompletedEventToPaymentResponseAvroModel(
          PaymentCompletedEvent paymentCompletedEvent) {
    return getPaymentResponseAvroModel(paymentCompletedEvent.getPayment(),
            paymentCompletedEvent.getCreatedAt(), paymentCompletedEvent.getFailureMessages(),
            paymentCompletedEvent);
  }

  /**
   * Payment cancelled event to payment response avro model payment response avro model.
   *
   * @param paymentCancelledEvent the payment cancelled event
   * @return the payment response avro model
   */
  public PaymentResponseAvroModel paymentCancelledEventToPaymentResponseAvroModel(
          PaymentCancelledEvent paymentCancelledEvent) {
    return getPaymentResponseAvroModel(paymentCancelledEvent.getPayment(),
            paymentCancelledEvent.getCreatedAt(), paymentCancelledEvent.getFailureMessages(),
            paymentCancelledEvent);
  }

  /**
   * Payment failed event to payment response avro model payment response avro model.
   *
   * @param paymentFailedEvent the payment failed event
   * @return the payment response avro model
   */
  public PaymentResponseAvroModel paymentFailedEventToPaymentResponseAvroModel(
          PaymentFailedEvent paymentFailedEvent) {
    return getPaymentResponseAvroModel(paymentFailedEvent.getPayment(),
            paymentFailedEvent.getCreatedAt(), paymentFailedEvent.getFailureMessages(),
            paymentFailedEvent);
  }

  private PaymentResponseAvroModel getPaymentResponseAvroModel(Payment payment,
                                                               ZonedDateTime createdAt, List<String> failureMessages, PaymentEvent domainEvent) {
    return PaymentResponseAvroModel.newBuilder().setId(UUID.randomUUID())
            .setSagaId(UUID.randomUUID()).setPaymentId(payment.getId().getBaseId())
            .setCustomerId(payment.getCustomerId().getBaseId())
            .setOrderId(payment.getOrderId().getBaseId()).setPrice(payment.getPrice().getAmount())
            .setCreatedAt(createdAt.toInstant())
            .setPaymentStatus(PaymentStatus.valueOf(payment.getPaymentStatus().name()))
            .setFailureMessages(failureMessages).build();
  }

  /**
   * Payment request avro model to payment request payment request.
   *
   * @param paymentRequestAvroModel the payment request avro model
   * @return the payment request
   */
  public PaymentRequest paymentRequestAvroModelToPaymentRequest(
          PaymentRequestAvroModel paymentRequestAvroModel) {
    return PaymentRequest.builder().id(paymentRequestAvroModel.getId().toString())
            .sagaId(paymentRequestAvroModel.getSagaId().toString())
            .customerId(paymentRequestAvroModel.getCustomerId().toString())
            .orderId(paymentRequestAvroModel.getOrderId().toString())
            .price(paymentRequestAvroModel.getPrice()).createdAt(paymentRequestAvroModel.getCreatedAt())
            .paymentOrderStatus(
                    PaymentOrderStatus.valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
            .build();
  }
}
