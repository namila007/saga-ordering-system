package me.namila.food_ordering.messaging.kafka.listener;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.food_ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import me.food_ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import me.namila.food_ordering.domain.application.ports.input.message.listener.restaurantapproval.RestaurantApprovalMessageListener;
import me.namila.food_ordering.kafka.consumer.service.KafkaConsumer;
import me.namila.food_ordering.messaging.mapper.OrderMessagingDataMapper;

/**
 * The type Payment response listener.
 */
@Slf4j
@Component
public class RestaurantResponseListener
    implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {
  private final RestaurantApprovalMessageListener restaurantApprovalMessageListener;
  private final OrderMessagingDataMapper orderMessagingDataMapper;

  public RestaurantResponseListener(
      RestaurantApprovalMessageListener restaurantApprovalMessageListener,
      OrderMessagingDataMapper orderMessagingDataMapper) {
    this.restaurantApprovalMessageListener = restaurantApprovalMessageListener;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
  }

  @Override
  @KafkaListener(id = "${kafka.consumer.config.restaurant-approval-consumer-group-id}",
      topics = "${order-service.restaurant-approval-response-topic-name}")
  public void receive(@Payload List<RestaurantApprovalResponseAvroModel> messages,
      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partition,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    log.info(
        "RestaurantResponseListener::receive - {} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
        messages.size(), keys.toString(), partition.toString(), offsets.toString());

    messages.forEach(approvalResponseAvroModel -> {
      if (approvalResponseAvroModel.getOrderApprovalStatus() == OrderApprovalStatus.APPROVED) {
        log.info(
            "RestaurantResponseListener::receive - Processing approved order for order id:: {}",
            approvalResponseAvroModel.getOrderId());
        restaurantApprovalMessageListener.orderApproved(
            orderMessagingDataMapper.toRestaurantApprovalResponseMsg(approvalResponseAvroModel));
      } else if (approvalResponseAvroModel
          .getOrderApprovalStatus() == OrderApprovalStatus.REJECTED) {
        log.info(
            "RestaurantResponseListener::receive - Processing rejected order for order id:: {}",
            approvalResponseAvroModel.getOrderId());
        restaurantApprovalMessageListener.orderRejected(
            orderMessagingDataMapper.toRestaurantApprovalResponseMsg(approvalResponseAvroModel));
      }
    });

  }
}
