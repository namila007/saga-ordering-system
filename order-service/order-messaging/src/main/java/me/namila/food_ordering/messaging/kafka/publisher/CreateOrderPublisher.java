package me.namila.food_ordering.messaging.kafka.publisher;

import me.namila.food_ordering.messaging.kafka.handler.PublisherMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.namila.food_ordering.domain.application.config.OrderServiceConfigData;
import me.namila.food_ordering.domain.application.constant.OrderServiceConstant;
import me.namila.food_ordering.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import me.namila.food_ordering.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Create order publisher.
 */
@Slf4j
@Component
public class CreateOrderPublisher implements OrderCreatedPaymentRequestMessagePublisher {

  private KafkaProducer<String, PaymentRequestAvroModel> KafkaProducer;
  private OrderMessagingDataMapper orderMessagingDataMapper;
  private OrderServiceConfigData orderServiceConfigData;
  private PublisherMessageHelper<String, PaymentRequestAvroModel> publisherMessageHelper;

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

  /**
   * Instantiates a new Create order publisher.
   *
   * @param KafkaProducer the kafka producer
   * @param orderMessagingDataMapper the order messaging data mapper
   * @param orderServiceConfigData the order service config data
   */
  public CreateOrderPublisher(KafkaProducer<String, PaymentRequestAvroModel> KafkaProducer,
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData) {
    this.KafkaProducer = KafkaProducer;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
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
              .apply(paymentRequestAvroModel.getOrderId().toString()));

      log.info("CreateOrderPublisher::publish - published create orderId: {}",
          paymentRequestAvroModel.getOrderId());
    } catch (Exception e) {
      log.error(
          "CreateOrderPublisher::publish -  exception occurred while publishing create orderId: {} , msg: {}",
          paymentRequestAvroModel.getOrderId(), e.getMessage(), e);
    }
  }
}
