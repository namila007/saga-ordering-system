package me.namila.food_ordering.order.domain.core.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.order.domain.core.entity.Order;
import me.namila.food_ordering.order.domain.core.entity.Restaurant;
import me.namila.food_ordering.order.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.order.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.order.domain.core.event.OrderPaidEvent;
import me.namila.food_ordering.order.domain.core.exception.OrderDomainException;

/**
 * The type Order domain service.
 */
@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
  private static final String TIME_ZONE = "UTC";

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant,
                                                    DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateOrder();
    order.initializeOrder();
    log.info(
        "OrderDomainServiceImpl::validateAndInitiateOrder - order with order id: {} is initiated",
        order.getId());
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)),
            orderCreatedEventDomainEventPublisher);
  }

  private void setOrderProductInformation(Order order, Restaurant restaurant) {

    order.getOrderItemList()
        .forEach(orderItem -> restaurant.getProducts().stream()
            .filter(restaurantProduct -> restaurantProduct.equals(orderItem.getProduct()))
            .findFirst().ifPresent(product -> {
              orderItem.getProduct().updateWithConfirmedValues(product);
            }));
  }

  private void validateRestaurant(Restaurant restaurant) {
    if (!restaurant.isActive())
      throw new OrderDomainException("Restaurant: " + restaurant.getId() + " is not active");
  }

  @Override
  public OrderPaidEvent payOrder(Order order,
                                 DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
    order.pay();
    log.info("OrderDomainServiceImpl::payOrder - order with order id: {} is paid", order.getId());
    return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)),
            orderPaidEventDomainEventPublisher);
  }

  @Override
  public void approveOrder(Order order) {
    order.approve();
    log.info("OrderDomainServiceImpl::approveOrder - order with order id: {} is approved",
        order.getId());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages,
                                                DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
    order.initCancel(failureMessages);
    log.info("OrderDomainServiceImpl::approveOrder - order with order id: {} is cancelling",
        order.getId().getBaseId());

    return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)),
            orderCancelledEventDomainEventPublisher);
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancelled(failureMessages);
    log.info("OrderDomainServiceImpl::cancelOrder - order with order id: {} is cancelled",
        order.getId().getBaseId());

  }
}
