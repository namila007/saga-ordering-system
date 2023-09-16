package me.namila.food_ordering.order.messaging.kafka.listener;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import me.food_ordering.system.kafka.order.avro.model.PaymentStatus;
import me.namila.food_ordering.kafka.consumer.service.KafkaConsumer;
import me.namila.food_ordering.order.domain.application.ports.input.message.listener.payment.PaymentResponseMessageListener;
import me.namila.food_ordering.order.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Payment response listener.
 */
@Slf4j
@Component
public class PaymentResponseListener implements KafkaConsumer<PaymentResponseAvroModel> {
  private final PaymentResponseMessageListener paymentResponseListener;
  private final OrderMessagingDataMapper orderMessagingDataMapper;

  /**
   * Instantiates a new Payment response listener.
   *
   * @param paymentResponseListener the payment response listener
   * @param orderMessagingDataMapper the order messaging data mapper
   */
  public PaymentResponseListener(PaymentResponseMessageListener paymentResponseListener,
      OrderMessagingDataMapper orderMessagingDataMapper) {
    this.paymentResponseListener = paymentResponseListener;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
  }

  @Override
  @KafkaListener(id = "${kafka.consumer.config.payment-consumer-group-id}",
      topics = "${order-service.payment-response-topic-name}")
  public void receive(@Payload List<PaymentResponseAvroModel> messages,
      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partition,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    log.info(
        "PaymentResponseListener::receive - {} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
        messages.size(), keys.toString(), partition.toString(), offsets.toString());
    messages.forEach(paymentResponseAvroModel -> {
      if (paymentResponseAvroModel.getPaymentStatus() == PaymentStatus.COMPLETED) {
        log.info("PaymentResponseListener::receive - processing successful payment for orderId: {}",
            paymentResponseAvroModel.getOrderId());
        paymentResponseListener
            .paymentCompleted(orderMessagingDataMapper.toPaymentResponse(paymentResponseAvroModel));
      } else if (paymentResponseAvroModel.getPaymentStatus() == PaymentStatus.CANCELLED) {
        log.info(
            "PaymentResponseListener::receive - processing unsuccessful payment for orderId: {}",
            paymentResponseAvroModel.getOrderId());
        paymentResponseListener
            .paymentCancelled(orderMessagingDataMapper.toPaymentResponse(paymentResponseAvroModel));
      }
    });

  }
}
