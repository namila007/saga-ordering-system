package me.namila.food_ordering.messaging.kafka.publisher;

import me.namila.food_ordering.messaging.kafka.handler.PublisherMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import me.namila.food_ordering.domain.application.config.OrderServiceConfigData;
import me.namila.food_ordering.domain.application.constant.OrderServiceConstant;
import me.namila.food_ordering.domain.application.ports.output.message.publisher.payment.OrderPaidPaymentRequestMessagePublisher;
import me.namila.food_ordering.domain.core.event.OrderPaidEvent;
import me.namila.food_ordering.kafka.producer.service.KafkaProducer;
import me.namila.food_ordering.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Create order publisher.
 */
@Slf4j
@Component
public class PayOrderPublisher implements OrderPaidPaymentRequestMessagePublisher {

  private KafkaProducer<String, RestaurantApprovalRequestAvroModel> KafkaProducer;
  private OrderMessagingDataMapper orderMessagingDataMapper;
  private OrderServiceConfigData orderServiceConfigData;
  private PublisherMessageHelper<String, RestaurantApprovalRequestAvroModel> publisherMessageHelper;

  public PayOrderPublisher(KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer,
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData,
      PublisherMessageHelper<String, RestaurantApprovalRequestAvroModel> publisherMessageHelper) {
    KafkaProducer = kafkaProducer;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
    this.publisherMessageHelper = publisherMessageHelper;
  }

  @Override
  public void publish(OrderPaidEvent domainEvent) {
    RestaurantApprovalRequestAvroModel requestAvroModel =
        orderMessagingDataMapper.toRestaurantApproval(domainEvent);
    log.info("PayOrderPublisher::publish - publishing pay order orderId: {}",
        requestAvroModel.getOrderId());

    try {
      KafkaProducer.send(
          orderServiceConfigData
              .getProperty(OrderServiceConstant.KafkaTopic.restaurantApprovalRequestTopicName),
          requestAvroModel.getOrderId().toString(), requestAvroModel,
          publisherMessageHelper.handleResponse.apply(requestAvroModel.getOrderId().toString()));

      log.info("PayOrderPublisher::publish - published pay order orderId: {}",
          requestAvroModel.getOrderId());
    } catch (Exception e) {
      log.error(
          "PayOrderPublisher::publish -  exception occurred while publishing pay order orderId: {} , msg: {}",
          requestAvroModel.getOrderId(), e.getMessage(), e);
    }
  }


}
