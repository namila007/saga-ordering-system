package me.namila.food_ordering.payment.messaging.kafka.listener;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.namila.food_ordering.kafka.consumer.service.KafkaConsumer;
import me.namila.food_ordering.payment.domain.ports.input.message.listener.PaymentRequestMessageListener;
import me.namila.food_ordering.payment.messaging.mapper.PaymentMessagingDataMapper;

/**
 * The type Payment request kafka listener.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {

  private final PaymentRequestMessageListener paymentRequestMessageListener;
  private final PaymentMessagingDataMapper paymentMessagingDataMapper;


  @Override
  @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}",
          topics = "${payment-service.payment-request-topic-name}")
  public void receive(@Payload List<PaymentRequestAvroModel> messages,
                      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    log.info(
            "PaymentRequestKafkaListener::receive - {} number of payment requests received with keys:{},"
                    + " partitions:{} and offsets: {}",
            messages.size(), keys.toString(), partitions.toString(), offsets.toString());

    messages.forEach(paymentRequestAvroModel -> {
      if (PaymentOrderStatus.PENDING == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("PaymentRequestKafkaListener::receive - Processing payment for order id: {}",
                paymentRequestAvroModel.getOrderId());
        paymentRequestMessageListener.completePayment(paymentMessagingDataMapper
                .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      } else if (PaymentOrderStatus.CANCELLED == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("PaymentRequestKafkaListener::receive - Cancelling payment for order id: {}",
                paymentRequestAvroModel.getOrderId());
        paymentRequestMessageListener.cancelPayment(paymentMessagingDataMapper
                .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      }
    });

  }
}
