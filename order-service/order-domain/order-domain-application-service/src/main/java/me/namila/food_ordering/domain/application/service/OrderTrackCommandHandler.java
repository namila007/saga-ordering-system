package me.namila.food_ordering.domain.application.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderQuery;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderResponse;
import me.namila.food_ordering.domain.application.exception.OrderNotFoundException;
import me.namila.food_ordering.domain.application.mapper.OrderDataMapper;
import me.namila.food_ordering.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.valueobject.TrackingId;

/**
 * The type Order track command handler.
 */
@Slf4j
@Component
public class OrderTrackCommandHandler {
  private final OrderDataMapper orderDataMapper;
  private final OrderRepository orderRepository;

  /**
   * Instantiates a new Order track command handler.
   *
   * @param orderDataMapper the order data mapper
   * @param orderRepository the order repository
   */
  public OrderTrackCommandHandler(OrderDataMapper orderDataMapper,
      OrderRepository orderRepository) {
    this.orderDataMapper = orderDataMapper;
    this.orderRepository = orderRepository;
  }

  /**
   * Track order track order response.
   *
   * @param trackOrderQuery the track order query
   * @return the track order response
   */
  @Transactional(readOnly = true)
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    Optional<Order> orderOptional =
        orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTracking()));
    if (orderOptional.isEmpty()) {
      log.error("OrderTrackCommandHandler::trackOrder - order: {} is missing on db",
          trackOrderQuery.getOrderTracking());
      throw new OrderNotFoundException(
          "order: " + trackOrderQuery.getOrderTracking() + " is missing on db");
    }
    return orderDataMapper.orderToTrackOrderResponse(orderOptional.get());
  }
}
