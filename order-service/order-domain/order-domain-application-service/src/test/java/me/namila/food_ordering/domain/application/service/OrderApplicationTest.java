package me.namila.food_ordering.domain.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.common.valueobject.OrderStatus;
import me.namila.food_ordering.common.valueobject.ProductId;
import me.namila.food_ordering.common.valueobject.RestaurantId;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.dto.create.OrderAddress;
import me.namila.food_ordering.domain.application.dto.create.OrderItem;
import me.namila.food_ordering.domain.application.mapper.OrderDataMapper;
import me.namila.food_ordering.domain.application.ports.output.repository.CustomerRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.RestaurantRepository;
import me.namila.food_ordering.domain.core.entity.Customer;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.Product;
import me.namila.food_ordering.domain.core.entity.Restaurant;
import me.namila.food_ordering.domain.core.exception.OrderDomainException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationTest {

  private final UUID CUSTOMER_ID = UUID.fromString("16950d30-3ed9-11ee-be56-0242ac120002");
  private final UUID RESTAURANT_ID = UUID.fromString("16950fec-3ed9-11ee-be56-0242ac120002");
  private final UUID PRODUCT_ID = UUID.fromString("16951262-3ed9-11ee-be56-0242ac120002");
  private final UUID ORDER_ID = UUID.fromString("169513b6-3ed9-11ee-be56-0242ac120002");
  private final BigDecimal PRICE = new BigDecimal("200.00");
  @Autowired
  private OrderApplicationService orderApplicationService;
  @Autowired
  private OrderDataMapper orderDataMapper;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RestaurantRepository restaurantRepository;
  private CreateOrderCommand createOrderCommand;
  private CreateOrderCommand createOrderCommandWrongPrice;
  private CreateOrderCommand createOrderCommandWrongProductPrice;

  @BeforeAll
  public void init() {
    createOrderCommand = CreateOrderCommand.builder().customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID).price(PRICE)
        .address(
            OrderAddress.builder().city("CMB").street("Duplication").postalCode("62000").build())
        .items(List.of(
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("50.00")).quantity(1)
                .subTotal(new BigDecimal("50.00")).build(),
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("50.00")).quantity(3)
                .subTotal(new BigDecimal("150.00")).build()))
        .build();

    createOrderCommandWrongPrice = CreateOrderCommand.builder().customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID).price(PRICE)
        .address(
            OrderAddress.builder().city("CMB").street("Duplication").postalCode("62000").build())
        .items(List.of(
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("50.00")).quantity(1)
                .subTotal(new BigDecimal("150.00")).build(),
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("50.00")).quantity(3)
                .subTotal(new BigDecimal("250.00")).build()))
        .build();

    createOrderCommandWrongProductPrice = CreateOrderCommand.builder().customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID).price(PRICE)
        .address(
            OrderAddress.builder().city("CMB").street("Duplication").postalCode("62000").build())
        .items(List.of(
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("150.00")).quantity(1)
                .subTotal(new BigDecimal("150.00")).build(),
            OrderItem.builder().productId(PRODUCT_ID).price(new BigDecimal("50.00")).quantity(3)
                .subTotal(new BigDecimal("150.00")).build()))
        .build();

    Customer customer = new Customer(new CustomerId(CUSTOMER_ID));

    Restaurant restaurant = Restaurant.Builder.builder()
        .restaurantId(new RestaurantId(RESTAURANT_ID))
        .products(List.of(
            new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
            new Product(new ProductId(PRODUCT_ID), "product-2",
                new Money(new BigDecimal("50.00")))))
        .isActive(true).build();

    Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    order.setId(new OrderId(ORDER_ID));

    Mockito
        .when(restaurantRepository.findRestaurantInformation(
            orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
        .thenReturn(Optional.of(restaurant));
    Mockito.when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
    Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
  }

  @Test
  public void testCreateOrder() {
    CreateOrderResponse createOrderResponse =
        orderApplicationService.createOrder(createOrderCommand);
    Assertions.assertEquals(createOrderResponse.getOrderStatus(), OrderStatus.PENDING);
    Assertions.assertNotNull(createOrderResponse.getOrderTrackingId());
  }

  @Test
  public void testCreateOrderWithWrongTotalPrice() {
    OrderDomainException orderDomainException = Assertions.assertThrows(OrderDomainException.class,
        () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
    Assertions.assertNotNull(orderDomainException);
  }

  @Test
  public void testCreateOrderWithWrongWrongProductPrice() {
    OrderDomainException orderDomainException = Assertions.assertThrows(OrderDomainException.class,
        () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
    Assertions.assertNotNull(orderDomainException);
  }
}
