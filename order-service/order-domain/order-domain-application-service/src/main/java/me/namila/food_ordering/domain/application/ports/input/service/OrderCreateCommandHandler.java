package me.namila.food_ordering.domain.application.ports.input.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.mapper.OrderDataMapper;
import me.namila.food_ordering.domain.application.ports.output.repository.CustomerRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.RestaurantRepository;
import me.namila.food_ordering.domain.core.entity.Customer;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.Restaurant;
import me.namila.food_ordering.domain.core.event.OrderCreatedEvent;
import me.namila.food_ordering.domain.core.exception.OrderDomainException;
import me.namila.food_ordering.domain.core.service.OrderDomainService;

@Slf4j
@Component
public class OrderCreateCommandHandler {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;

  private final OrderDataMapper orderDataMapper;

  @Autowired
  public OrderCreateCommandHandler(OrderDomainService orderDomainService,
      OrderRepository orderRepository, CustomerRepository customerRepository,
      RestaurantRepository restaurantRepository, OrderDataMapper orderDataMapper) {
    this.orderDomainService = orderDomainService;
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
    this.restaurantRepository = restaurantRepository;
    this.orderDataMapper = orderDataMapper;
  }

  @Transactional
  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    checkCustomer(createOrderCommand.getCustomerId());
    Restaurant restaurant = retrieveRestaurant(createOrderCommand);
    Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    OrderCreatedEvent validatedOrder =
        orderDomainService.validateAndInitiateOrder(order, restaurant);
    Order orderResult = saveOrder(order);
    return orderDataMapper.orderToCreateOrderResponse(orderResult);
  }


  private Order saveOrder(Order order) {
    Order orderResult = orderRepository.save(order);
    if (order == null) {
      log.error("Order: {} , is not saved", order.getId());
      throw new OrderDomainException("Order: " + order.getId() + " , is not saved");
    }
    log.info("Order: {} , is saved", order.getId());
    return orderResult;
  }

  private Restaurant retrieveRestaurant(CreateOrderCommand createOrderCommand) {
    Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
    Optional<Restaurant> restaurantOptional =
        restaurantRepository.findRestaurantInformation(restaurant);
    if (restaurantOptional.isEmpty()) {
      log.error("Restaurant: {} , is not available for the order",
          createOrderCommand.getRestaurantId());
      throw new OrderDomainException("Restaurant: " + createOrderCommand.getRestaurantId()
          + " , is not available for the order");
    }
    return restaurantOptional.get();
  }


  private void checkCustomer(UUID customerId) {
    Optional<Customer> customerOptional = customerRepository.findCustomer(customerId);
    if (customerOptional.isEmpty()) {
      log.error("Customer: {} , is not available for the order", customerId);
      throw new OrderDomainException(
          "Customer: " + customerId + " , is not available for the order");
    }
  }
}
