package me.namila.food_ordering.order.messaging.kafka.publisher;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.order.domain.application.config.OrderServiceConfigData;
import me.namila.food_ordering.order.domain.application.constant.OrderServiceConstant;
import me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import me.namila.food_ordering.order.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.order.messaging.kafka.handler.PublisherMessageHelper;
import me.namila.food_ordering.order.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Create order publisher.
 */
@Slf4j
@Component
public class CancelOrderPublisher implements OrderCancelledPaymentRequestMessagePublisher {

  private KafkaProducer<String, PaymentRequestAvroModel> KafkaProducer;
  private OrderMessagingDataMapper orderMessagingDataMapper;
  private OrderServiceConfigData orderServiceConfigData;
  private PublisherMessageHelper<String, PaymentRequestAvroModel> publisherMessageHelper;

  /**
   * Instantiates a new Create order publisher.
   *
   * @param KafkaProducer the kafka producer
   * @param orderMessagingDataMapper the order messaging data mapper
   * @param orderServiceConfigData the order service config data
   */
  public CancelOrderPublisher(KafkaProducer<String, PaymentRequestAvroModel> KafkaProducer,
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData,
      PublisherMessageHelper<String, PaymentRequestAvroModel> publisherMessageHelper) {
    this.KafkaProducer = KafkaProducer;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
    this.publisherMessageHelper = publisherMessageHelper;
  }

  @Override
  public void publish(OrderCancelledEvent domainEvent) {
    PaymentRequestAvroModel paymentRequestAvroModel =
        orderMessagingDataMapper.cancelledEventToPaymentRequestAvroModel(domainEvent);
    log.info("CancelOrderPublisher::publish - cancelling orderId: {}",
        paymentRequestAvroModel.getOrderId());

    try {
      KafkaProducer.send(
          orderServiceConfigData
              .getProperty(OrderServiceConstant.KafkaTopic.paymentRequestTopicName),
          paymentRequestAvroModel.getOrderId().toString(), paymentRequestAvroModel,
          publisherMessageHelper.handleResponse
              .apply(paymentRequestAvroModel.getOrderId().toString()));

      log.info("CreateOrderPublisher::publish - canceled orderId: {}",
          paymentRequestAvroModel.getOrderId());
    } catch (Exception e) {
      log.error(
          "CreateOrderPublisher::publish -  exception occurred while publishing orderId: {} , msg: {}",
          paymentRequestAvroModel.getOrderId(), e.getMessage(), e);
    }
  }
}
