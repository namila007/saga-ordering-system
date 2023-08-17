package me.namila.food_ordering.domain.core.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.Restaurant;
import me.namila.food_ordering.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.domain.core.event.OrderPaidEvent;
import me.namila.food_ordering.domain.core.exception.OrderDomainException;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
  private static String TIME_ZONE = "IST";

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateOrder();
    order.initializeOrder();
    log.info(
        "OrderDomainServiceImpl::validateAndInitiateOrder - order with order id: {} is initiated",
        order.getId());
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)));
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
  public OrderPaidEvent payOrder(Order order) {
    order.pay();
    log.info("OrderDomainServiceImpl::payOrder - order with order id: {} is paid", order.getId());
    return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)));
  }

  @Override
  public void approveOrder(Order order) {
    order.approve();
    log.info("OrderDomainServiceImpl::approveOrder - order with order id: {} is approved",
        order.getId());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
    order.initCancel(failureMessages);
    log.info("OrderDomainServiceImpl::approveOrder - order with order id: {} is cancelling",
        order.getId().getBaseId());
    return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(TIME_ZONE)));
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancelled(failureMessages);
    log.info("OrderDomainServiceImpl::cancelOrder - order with order id: {} is cancelled",
        order.getId().getBaseId());

  }
}
