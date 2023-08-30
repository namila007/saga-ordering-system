package me.namila.food_ordering.dataaccess.order.adapter;

import java.util.Optional;

import me.namila.food_ordering.dataaccess.order.mapper.OrderDataAccessMapper;
import me.namila.food_ordering.dataaccess.order.repository.OrderJpaRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.valueobject.TrackingId;

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
