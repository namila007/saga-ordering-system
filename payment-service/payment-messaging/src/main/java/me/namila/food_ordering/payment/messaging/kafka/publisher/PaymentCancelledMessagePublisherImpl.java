package me.namila.food_ordering.payment.messaging.kafka.publisher;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import me.namila.food_ordering.kafka.producer.handler.PublisherMessageHelper;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.payment.domain.config.PaymentServiceConfigData;
import me.namila.food_ordering.payment.domain.constant.PaymentServiceConstant;
import me.namila.food_ordering.payment.domain.event.PaymentCancelledEvent;
import me.namila.food_ordering.payment.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import me.namila.food_ordering.payment.messaging.mapper.PaymentMessagingDataMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentCancelledMessagePublisherImpl implements PaymentCancelledMessagePublisher {
  private final PaymentMessagingDataMapper paymentMessagingDataMapper;
  private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
  private final PaymentServiceConfigData paymentServiceConfigData;
  private final PublisherMessageHelper<String, PaymentResponseAvroModel> publisherMessageHelper;

  @Override
  public void publish(PaymentCancelledEvent domainEvent) {
    String orderId = domainEvent.getPayment().getOrderId().getBaseId().toString();
    log.info(
            "PaymentCancelledMessagePublisherImpl::publish - Received PaymentCancelledEvent for order id: {}",
            orderId);
    try {
      PaymentResponseAvroModel responseAvroModel =
              paymentMessagingDataMapper.paymentCancelledEventToPaymentResponseAvroModel(domainEvent);
      kafkaProducer.send(
              paymentServiceConfigData
                      .getProperty(PaymentServiceConstant.KafkaTopic.paymentResponseTopicName),
              orderId, responseAvroModel, publisherMessageHelper.handleResponse
                      .apply(responseAvroModel.getOrderId().toString(), responseAvroModel));
      log.info(
              "PaymentCancelledMessagePublisherImpl::publish - PaymentResponseAvroModel sent to kafka for order id: {}",
              orderId);
    } catch (Exception e) {
      log.error(
              "PaymentCancelledMessagePublisherImpl::publish - Error while sending PaymentResponseAvroModel message"
                      + " to kafka with order id: {}, error: {}",
              orderId, e.getMessage());
    }
  }
}
