package me.namila.food_ordering.order.domain.application.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.order.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.order.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.order.domain.application.mapper.OrderDataMapper;
import me.namila.food_ordering.order.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

/**
 * The type Order create command handler.
 */
@Slf4j
@Component
public class OrderCreateCommandHandler {


  private final OrderCreateHelper orderCreateHelper;
  private final OrderDataMapper orderDataMapper;
  private final OrderCreatedPaymentRequestMessagePublisher paymentRequestMessagePublisher;

  /**
   * Instantiates a new Order create command handler.
   *
   * @param orderCreateHelper the order create helper
   * @param orderDataMapper the order data mapper
   * @param paymentRequestMessagePublisher the payment request message publisher
   */
  public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper,
      OrderDataMapper orderDataMapper,
      OrderCreatedPaymentRequestMessagePublisher paymentRequestMessagePublisher) {
    this.orderCreateHelper = orderCreateHelper;
    this.orderDataMapper = orderDataMapper;
    this.paymentRequestMessagePublisher = paymentRequestMessagePublisher;
  }

  /**
   * Create order create order response.
   *
   * @param createOrderCommand the create order command
   * @return the create order response
   */
  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    var orderResult = orderCreateHelper.persistOrder(createOrderCommand);
    log.info("order: {} is persisted", orderResult.getOrder().getId());
    paymentRequestMessagePublisher.publish(orderResult);
    return orderDataMapper.orderToCreateOrderResponse(orderResult.getOrder());
  }



}
