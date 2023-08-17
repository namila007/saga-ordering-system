package me.namila.food_ordering.domain.application.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.ProductId;
import me.namila.food_ordering.common.valueobject.RestaurantId;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.dto.create.OrderAddress;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.OrderItem;
import me.namila.food_ordering.domain.core.entity.Product;
import me.namila.food_ordering.domain.core.entity.Restaurant;
import me.namila.food_ordering.domain.core.valueobject.StreetAddress;

@Component
public class OrderDataMapper {

  public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
    return Restaurant.Builder.builder()
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .products(createOrderCommand.getItems().stream()
            .map(orderItem -> new Product(new ProductId(orderItem.getProductId()))).toList())
        .build();
  }

  public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
    return Order.Builder.builder().customerId(new CustomerId(createOrderCommand.getCustomerId()))
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .deliveryAddress(createOrderAddressToDeliveryAddress(createOrderCommand.getAddress()))
        .price(new Money(createOrderCommand.getPrice()))
        .orderItemList(orderItemToOrderItemEntities(createOrderCommand.getItems())).build();
  }

  public CreateOrderResponse orderToCreateOrderResponse(Order order) {
    return CreateOrderResponse.builder().orderStatus(order.getOrderStatus())
        .orderTrackingId(order.getTrackingId().getBaseId()).build();
  }

  private List<OrderItem> orderItemToOrderItemEntities(
      @NonNull List<me.namila.food_ordering.domain.application.dto.create.OrderItem> items) {
    return items.stream()
        .map(orderItem -> OrderItem.Builder.builder()
            .product(new Product(new ProductId(orderItem.getProductId())))
            .price(new Money(orderItem.getPrice())).quantity(orderItem.getQuantity())
            .subTotal(new Money(orderItem.getSubTotal())).build())
        .toList();
  }

  private StreetAddress createOrderAddressToDeliveryAddress(OrderAddress address) {
    return new StreetAddress(UUID.randomUUID(), address.getStreet(), address.getPostalCode(),
        address.getCity());
  }
}
