package me.namila.food_ordering.order.dataaccess.order.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.order.dataaccess.order.mapper.OrderDataAccessMapper;
import me.namila.food_ordering.order.dataaccess.order.repository.OrderJpaRepository;
import me.namila.food_ordering.order.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.order.domain.core.entity.Order;
import me.namila.food_ordering.order.domain.core.valueobject.TrackingId;

@Component
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;
  private final OrderDataAccessMapper orderDataAccessMapper;

  public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
      OrderDataAccessMapper orderDataAccessMapper) {
    this.orderJpaRepository = orderJpaRepository;
    this.orderDataAccessMapper = orderDataAccessMapper;
  }

  @Override
  public Order save(Order order) {

    return orderDataAccessMapper.fromOrderEntityToOrder(
        orderJpaRepository.save(orderDataAccessMapper.fromOrderToOrderEntity(order)));
  }

  @Override
  public Optional<Order> findByTrackingId(TrackingId trackingId) {
    return orderJpaRepository.findByTrackingId(trackingId.getBaseId())
        .map(orderDataAccessMapper::fromOrderEntityToOrder);
  }
}
