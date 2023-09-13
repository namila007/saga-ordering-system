package me.namila.food_ordering.dataaccess.order.mapper;

import static me.namila.food_ordering.domain.core.entity.Order.FAILURE_MESSAGE_DELIMITER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.dataaccess.order.entity.OrderAddressEntity;
import me.namila.food_ordering.dataaccess.order.entity.OrderEntity;
import me.namila.food_ordering.dataaccess.order.entity.OrderItemEntity;
import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.entity.OrderItem;
import me.namila.food_ordering.domain.core.valueobject.StreetAddress;

/**
 * The type Order data access mapper.
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
    builder = @Builder(disableBuilder = true))
public abstract class OrderDataAccessMapper {


  /**
   * From order to order entity order entity.
   *
   * @param order the order
   * @return the order entity
   */
  @Mapping(source = "id.baseId", target = "id")
  @Mapping(source = "customerId.baseId", target = "customerId")
  @Mapping(source = "restaurantId.baseId", target = "restaurantId")
  @Mapping(source = "trackingId.baseId", target = "trackingId")
  @Mapping(source = "deliveryAddress", target = "address")
  @Mapping(target = "items", expression = "java(toOrderItemEntityList(order.getOrderItemList()))")
  @Mapping(target = "price", expression = "java(order.getPrice().getAmount())")
  @Mapping(target = "failureMessages",
      expression = "java(toFailureMessagesString(order.getFailureMessages()))")
  public abstract OrderEntity fromOrderToOrderEntity(Order order);

  /**
   * Update order entity.
   *
   * @param orderEntity the order entity
   */
  @AfterMapping
  protected void updateOrderEntity(@MappingTarget OrderEntity orderEntity) {
    orderEntity.getAddress().setOrder(orderEntity);
    orderEntity.getItems()
        .forEach(orderItemEntity -> orderItemEntity.getOrderItem().setOrder(orderEntity));

  }

  /**
   * To order address entity order address entity.
   *
   * @param streetAddress the street address
   * @return the order address entity
   */
  protected abstract OrderAddressEntity toOrderAddressEntity(StreetAddress streetAddress);

  /**
   * To failure messages string string.
   *
   * @param failureMessages the failure messages
   * @return the string
   */
  protected String toFailureMessagesString(List<String> failureMessages) {
    return Objects.isNull(failureMessages) ? ""
        : String.join(FAILURE_MESSAGE_DELIMITER, failureMessages);
  }

  /**
   * To order item entity list list.
   *
   * @param orderItems the order items
   * @return the list
   */
  protected abstract List<OrderItemEntity> toOrderItemEntityList(List<OrderItem> orderItems);

  /**
   * From order item entity to order item order item entity.
   *
   * @param orderItem the order item
   * @return the order item entity
   */
  @Mapping(source = "product.id.baseId", target = "productId")
  @Mapping(target = "subTotal", expression = "java(orderItem.getSubTotal().getAmount())")
  @Mapping(target = "price", expression = "java(orderItem.getPrice().getAmount())")
  @Mapping(target = "orderItem.id", source = "id.baseId")
  protected abstract OrderItemEntity fromOrderItemEntityToOrderItem(OrderItem orderItem);


  /**
   * From order entity to order order.
   *
   * @param orderEntity the order entity
   * @return the order
   */
  @InheritInverseConfiguration
  public abstract Order fromOrderEntityToOrder(OrderEntity orderEntity);


  /**
   * To money money.
   *
   * @param bigDecimal the big decimal
   * @return the money
   */
  protected Money toMoney(BigDecimal bigDecimal) {
    return new Money(bigDecimal);
  }

  /**
   * To failure messages list string list.
   *
   * @param failureMessages the failure messages
   * @return the list
   */
  protected List<String> toFailureMessagesListString(String failureMessages) {
    return Objects.isNull(failureMessages) ? new ArrayList<>()
        : Arrays.stream(failureMessages.split(FAILURE_MESSAGE_DELIMITER)).toList();
  }
}
