package me.namila.food_ordering.messaging.mapper;

import me.food_ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import me.namila.food_ordering.domain.core.event.OrderCancelledEvent;
import me.namila.food_ordering.domain.core.event.OrderCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

/**
 * The type Order messaging data mapper.
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class OrderMessagingDataMapper {

  /**
   * To payment request avro model payment request avro model.
   *
   * @param orderCreatedEvent the order created event
   * @return the payment request avro model
   */
  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "orderId", source = "order.id.baseId")
  @Mapping(target = "sagaId", expression = "java(UUID.randomUUID())")
  @Mapping(target = "customerId", source = "order.customerId.baseId")
  @Mapping(target = "price", source = "order.price.amount")
  @Mapping(target = "createdAt", expression = "java(orderCreatedEvent.getCreatedAt().toInstant())")
  public abstract PaymentRequestAvroModel createdEventToPaymentRequestAvroModel(
      OrderCreatedEvent orderCreatedEvent);

  /**
   * Convert order status me . food ordering . system . kafka . order . avro . model . payment order
   * status.
   *
   * @param orderStatus the order status
   * @return the me . food ordering . system . kafka . order . avro . model . payment order status
   */
  @ValueMappings({@ValueMapping(source = "PENDING", target = "PENDING"),
      @ValueMapping(source = "CANCELLED", target = "CANCELLED"),
      @ValueMapping(source = MappingConstants.ANY_REMAINING, target = "PENDING"),
      @ValueMapping(source = MappingConstants.NULL, target = "PENDING")})
  protected abstract me.food_ordering.system.kafka.order.avro.model.PaymentOrderStatus convertOrderStatus(
      me.namila.food_ordering.common.valueobject.OrderStatus orderStatus);

  /**
   * To payment request avro model payment request avro model.
   *
   * @param orderCancelledEvent the order cancelled event
   * @return the payment request avro model
   */
  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "orderId", source = "order.id.baseId")
  @Mapping(target = "sagaId", expression = "java(UUID.randomUUID())")
  @Mapping(target = "customerId", source = "order.customerId.baseId")
  @Mapping(target = "price", source = "order.price.amount")
  @Mapping(target = "createdAt",
      expression = "java(orderCancelledEvent.getCreatedAt().toInstant())")
  public abstract PaymentRequestAvroModel cancelledEventToPaymentRequestAvroModel(
      OrderCancelledEvent orderCancelledEvent);
}
