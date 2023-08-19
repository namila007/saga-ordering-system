package me.namila.food_ordering.domain.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderQuery;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderResponse;

@Service
@Validated
@Slf4j
class OrderApplicationServiceImpl implements OrderApplicationService {

  private final OrderTrackCommandHandler orderTrackCommandHandler;
  private final OrderCreateCommandHandler orderCreateCommandHandler;

  @Autowired
  public OrderApplicationServiceImpl(OrderTrackCommandHandler orderTrackCommandHandler,
      OrderCreateCommandHandler orderCreateCommandHandler) {
    this.orderTrackCommandHandler = orderTrackCommandHandler;
    this.orderCreateCommandHandler = orderCreateCommandHandler;
  }

  @Override
  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    return orderCreateCommandHandler.createOrder(createOrderCommand);
  }

  @Override
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    return orderTrackCommandHandler.trackOrder(trackOrderQuery);
  }
}
