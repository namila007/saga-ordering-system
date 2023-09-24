package me.namila.food_ordering.order.messaging.kafka.publisher;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.namila.food_ordering.kafka.producer.handler.PublisherMessageHelper;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.order.domain.application.config.OrderServiceConfigData;
import me.namila.food_ordering.order.domain.application.constant.OrderServiceConstant;
import me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import me.namila.food_ordering.order.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.order.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Create order publisher.
 */
@Slf4j
@Component
public class CreateOrderPublisher implements OrderCreatedPaymentRequestMessagePublisher {

  private final KafkaProducer<String, PaymentRequestAvroModel> KafkaProducer;
  private final OrderMessagingDataMapper orderMessagingDataMapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final PublisherMessageHelper<String, PaymentRequestAvroModel> publisherMessageHelper;

  public CreateOrderPublisher(
      me.namila.food_ordering.kafka.producer.service.KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer,
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData,
      PublisherMessageHelper<String, PaymentRequestAvroModel> publisherMessageHelper) {
    KafkaProducer = kafkaProducer;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
    this.publisherMessageHelper = publisherMessageHelper;
  }

  @Override
  public void publish(OrderCreatedEvent domainEvent) {
    PaymentRequestAvroModel paymentRequestAvroModel =
        orderMessagingDataMapper.createdEventToPaymentRequestAvroModel(domainEvent);
    log.info("CreateOrderPublisher::publish - publishing create orderId: {}",
        paymentRequestAvroModel.getOrderId());

    try {
      KafkaProducer.send(
          orderServiceConfigData
              .getProperty(OrderServiceConstant.KafkaTopic.paymentRequestTopicName),
          paymentRequestAvroModel.getOrderId().toString(), paymentRequestAvroModel,
          publisherMessageHelper.handleResponse
                  .apply(paymentRequestAvroModel.getOrderId().toString(), paymentRequestAvroModel));

      log.info("CreateOrderPublisher::publish - published create orderId: {}",
          paymentRequestAvroModel.getOrderId());
    } catch (Exception e) {
      log.error(
          "CreateOrderPublisher::publish -  exception occurred while publishing create orderId: {} , msg: {}",
          paymentRequestAvroModel.getOrderId(), e.getMessage(), e);
    }
  }
}
