package me.namila.food_ordering.order.domain.core.entity;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.namila.food_ordering.common.entity.AggregateRoot;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.common.valueobject.OrderStatus;
import me.namila.food_ordering.common.valueobject.RestaurantId;
import me.namila.food_ordering.order.domain.core.exception.OrderDomainException;
import me.namila.food_ordering.order.domain.core.valueobject.OrderItemId;
import me.namila.food_ordering.order.domain.core.valueobject.StreetAddress;
import me.namila.food_ordering.order.domain.core.valueobject.TrackingId;

/**
 * The type Order.
 */
@Getter
@AllArgsConstructor
public class Order extends AggregateRoot<OrderId> {
  public static final String FAILURE_MESSAGE_DELIMITER = ",";

  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress deliveryAddress;
  private final Money price;
  private final List<OrderItem> orderItemList;
  private List<String> failureMessages;
  private TrackingId trackingId;
  private OrderStatus orderStatus;

  private Order(Builder builder) {
    super.setId(builder.orderId);
    customerId = builder.customerId;
    restaurantId = builder.restaurantId;
    deliveryAddress = builder.deliveryAddress;
    price = builder.price;
    orderItemList = builder.orderItemList;
    trackingId = builder.trackingId;
    orderStatus = builder.orderStatus;
    failureMessages = builder.failureMessages;
  }

  /**
   * Initialize order.
   */
  public void initializeOrder() {
    setId(new OrderId(UUID.randomUUID()));
    trackingId = new TrackingId(UUID.randomUUID());
    orderStatus = OrderStatus.PENDING;
    initializeOrderItem();
  }

  /**
   * Validate order.
   */
  public void validateOrder() {
    validateInitialOrder();
    validateTotalPrice();
    validateItemsPrice();
  }

  /**
   * Pay.
   */
  public void pay() {
    if (orderStatus != OrderStatus.PENDING)
      throw new OrderDomainException("Order is not in correct state for Pay ops");
    orderStatus = OrderStatus.PAID;
  }

  /**
   * Approve.
   */
  public void approve() {
    if (orderStatus != OrderStatus.PAID)
      throw new OrderDomainException("Order is not in correct state for Approve ops");
    orderStatus = OrderStatus.APPROVED;
  }

  /**
   * Init cancel.
   *
   * @param failureMessages the failure messages
   */
  public void initCancel(List<String> failureMessages) {
    if (orderStatus != OrderStatus.PAID)
      throw new OrderDomainException("Order is not in correct state for Cancelling ops");
    orderStatus = OrderStatus.CANCELLING;
    updateFailureMessages(failureMessages);
  }

  /**
   * Cancelled.
   *
   * @param failureMessages the failure messages
   */
  public void cancelled(List<String> failureMessages) {
    if (orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING) {
      orderStatus = OrderStatus.CANCELLED;
      updateFailureMessages(failureMessages);
    } else
      throw new OrderDomainException("Order is not in correct state for Cancelled ops");
  }

  private void updateFailureMessages(List<String> failureMessages) {
    if (failureMessages != null && this.failureMessages != null) {
      this.failureMessages.addAll(failureMessages.stream().filter(msg -> !msg.isEmpty()).toList());
    } else if (this.failureMessages == null) {
      this.failureMessages = failureMessages;

    }
  }

  private void validateItemsPrice() {
    var totalOrderItemValue = orderItemList.stream().map(orderItem -> {
      validateItemPrice(orderItem);
      return orderItem.getSubTotal();
    }).reduce(Money.ZERO_MONEY, Money::add);

    if (!price.equals(totalOrderItemValue)) {
      throw new OrderDomainException("Total price: " + price.getAmount()
              + " is not equal to order item total " + ":" + totalOrderItemValue.getAmount());
    }

  }

  private void validateItemPrice(OrderItem orderItem) {
    if (!orderItem.isPriceValid())
      throw new OrderDomainException("order item price: " + orderItem.getPrice().getAmount());
  }

  private void validateTotalPrice() {
    if (price == null || !price.isAmountGreaterThanZero()) {
      throw new OrderDomainException("Total price must greater than zero");
    }
  }

  private void validateInitialOrder() {
    if (orderStatus != null || getId() != null) {
      throw new OrderDomainException("Order is not in correct state to initialize the order");
    }
  }

  private void initializeOrderItem() {
    long orderItemId = 1L;
    for (var orderItem : orderItemList) {
      orderItem.initializeOrderItem(new OrderItemId(orderItemId++), super.getId());
    }
  }


  /**
   * {@code Order} builder static inner class.
   */
  public static final class Builder {
    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress deliveryAddress;
    private Money price;
    private List<OrderItem> orderItemList;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Builder() {
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
      return new Builder();
    }

    /**
     * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
     *
     * @param orderId the {@code id} to set
     * @return a reference to this Builder
     */
    public Builder orderId(OrderId orderId) {
      this.orderId = orderId;
      return this;
    }

    /**
     * Sets the {@code customerId} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code customerId} to set
     * @return a reference to this Builder
     */
    public Builder customerId(CustomerId val) {
      customerId = val;
      return this;
    }

    /**
     * Sets the {@code restaurantId} and returns a reference to this Builder enabling method
     * chaining.
     *
     * @param val the {@code restaurantId} to set
     * @return a reference to this Builder
     */
    public Builder restaurantId(RestaurantId val) {
      restaurantId = val;
      return this;
    }

    /**
     * Sets the {@code streetAddress} and returns a reference to this Builder enabling method
     * chaining.
     *
     * @param val the {@code streetAddress} to set
     * @return a reference to this Builder
     */
    public Builder deliveryAddress(StreetAddress val) {
      deliveryAddress = val;
      return this;
    }

    /**
     * Sets the {@code money} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code money} to set
     * @return a reference to this Builder
     */
    public Builder price(Money val) {
      price = val;
      return this;
    }

    /**
     * Sets the {@code orderItemList} and returns a reference to this Builder enabling method
     * chaining.
     *
     * @param val the {@code orderItemList} to set
     * @return a reference to this Builder
     */
    public Builder orderItemList(List<OrderItem> val) {
      orderItemList = val;
      return this;
    }

    /**
     * Sets the {@code trackingId} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code trackingId} to set
     * @return a reference to this Builder
     */
    public Builder trackingId(TrackingId val) {
      trackingId = val;
      return this;
    }

    /**
     * Sets the {@code orderStatus} and returns a reference to this Builder enabling method
     * chaining.
     *
     * @param val the {@code orderStatus} to set
     * @return a reference to this Builder
     */
    public Builder orderStatus(OrderStatus val) {
      orderStatus = val;
      return this;
    }

    /**
     * Sets the {@code failureMessages} and returns a reference to this Builder enabling method
     * chaining.
     *
     * @param val the {@code failureMessages} to set
     * @return a reference to this Builder
     */
    public Builder failureMessages(List<String> val) {
      failureMessages = val;
      return this;
    }

    /**
     * Returns a {@code Order} built from the parameters previously set.
     *
     * @return a {@code Order} built with parameters of this {@code Order.Builder}
     */
    public Order build() {
      return new Order(this);
    }
  }
}
