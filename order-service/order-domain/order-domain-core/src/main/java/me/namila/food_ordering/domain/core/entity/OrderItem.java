package me.namila.food_ordering.domain.core.entity;

import lombok.Getter;
import me.namila.food_ordering.common.entity.BaseEntity;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.domain.core.valueobject.OrderItemId;

/**
 * The type Order item.
 */
@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
  private final Product product;
  private final Integer quantity;
  private final Money price;
  private final Money subTotal;
  private OrderId orderId;

  private OrderItem(Builder builder) {
    super.setId(builder.id);
    product = builder.product;
    quantity = builder.quantity;
    price = builder.price;
    subTotal = builder.subTotal;
  }

  protected void initializeOrderItem(OrderItemId orderItemId, OrderId id) {
    setId(orderItemId);
    this.orderId = id;
  }

  protected boolean isPriceValid() {
      return price.isAmountGreaterThanZero() && price.equals(product.getMoney())
              && subTotal.equals(price.multiply(quantity));
  }


  /**
   * {@code OrderItem} builder static inner class.
   */
  public static final class Builder {
    private OrderItemId id;
    private Product product;
    private Integer quantity;
    private Money price;
    private Money subTotal;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    /**
     * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
     *
     * @param orderItemId the {@code id} to set
     * @return a reference to this Builder
     */
    public Builder orderItemId(OrderItemId orderItemId) {
      id = orderItemId;
      return this;
    }

    /**
     * Sets the {@code product} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code product} to set
     * @return a reference to this Builder
     */
    public Builder product(Product val) {
      product = val;
      return this;
    }

    /**
     * Sets the {@code quantity} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code quantity} to set
     * @return a reference to this Builder
     */
    public Builder quantity(Integer val) {
      quantity = val;
      return this;
    }

    /**
     * Sets the {@code price} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code price} to set
     * @return a reference to this Builder
     */
    public Builder price(Money val) {
      price = val;
      return this;
    }

    /**
     * Sets the {@code subTotal} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code subTotal} to set
     * @return a reference to this Builder
     */
    public Builder subTotal(Money val) {
      subTotal = val;
      return this;
    }

    /**
     * Returns a {@code OrderItem} built from the parameters previously set.
     *
     * @return a {@code OrderItem} built with parameters of this {@code OrderItem.Builder}
     */
    public OrderItem build() {
      return new OrderItem(this);
    }
  }
}
