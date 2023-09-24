package me.namila.food_ordering.payment.messaging.kafka.publisher;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import me.namila.food_ordering.kafka.producer.handler.PublisherMessageHelper;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.payment.domain.config.PaymentServiceConfigData;
import me.namila.food_ordering.payment.domain.constant.PaymentServiceConstant;
import me.namila.food_ordering.payment.domain.event.PaymentFailedEvent;
import me.namila.food_ordering.payment.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import me.namila.food_ordering.payment.messaging.mapper.PaymentMessagingDataMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentFailedMessagePublisherImpl implements PaymentFailedMessagePublisher {
  private final PaymentMessagingDataMapper paymentMessagingDataMapper;
  private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
  private final PaymentServiceConfigData paymentServiceConfigData;
  private final PublisherMessageHelper<String, PaymentResponseAvroModel> publisherMessageHelper;

  @Override
  public void publish(PaymentFailedEvent domainEvent) {
    String orderId = domainEvent.getPayment().getOrderId().getBaseId().toString();
    log.info(
            "PaymentFailedMessagePublisherImpl::publish - Received PaymentFailedEvent for order id: {}",
            orderId);
    try {
      PaymentResponseAvroModel responseAvroModel =
              paymentMessagingDataMapper.paymentFailedEventToPaymentResponseAvroModel(domainEvent);
      kafkaProducer.send(
              paymentServiceConfigData
                      .getProperty(PaymentServiceConstant.KafkaTopic.paymentResponseTopicName),
              orderId, responseAvroModel, publisherMessageHelper.handleResponse
                      .apply(responseAvroModel.getOrderId().toString(), responseAvroModel));
      log.info(
              "PaymentFailedMessagePublisherImpl::publish - PaymentResponseAvroModel sent to kafka for order id: {}",
              orderId);
    } catch (Exception e) {
      log.error(
              "PaymentFailedMessagePublisherImpl::publish - Error while sending PaymentResponseAvroModel message"
                      + " to kafka with order id: {}, error: {}",
              orderId, e.getMessage());
    }
  }
}
