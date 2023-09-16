package me.namila.food_ordering.order.domain.core.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.namila.food_ordering.common.entity.AggregateRoot;
import me.namila.food_ordering.common.valueobject.RestaurantId;

/**
 * The type Restaurant.
 */
@Getter
@AllArgsConstructor
public class Restaurant extends AggregateRoot<RestaurantId> {
  private final List<Product> products;
  private final boolean isActive;

  private Restaurant(Builder builder) {
    super.setId(builder.restaurantId);
    products = builder.products;
    isActive = builder.isActive;
  }


  /**
   * {@code Restaurant} builder static inner class.
   */
  public static final class Builder {
    private RestaurantId restaurantId;
    private List<Product> products;
    private boolean isActive;

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
     * @param restaurantId the {@code id} to set
     * @return a reference to this Builder
     */
    public Builder restaurantId(RestaurantId restaurantId) {
      this.restaurantId = restaurantId;
      return this;
    }

    /**
     * Sets the {@code products} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code products} to set
     * @return a reference to this Builder
     */
    public Builder products(List<Product> val) {
      products = val;
      return this;
    }

    /**
     * Sets the {@code isActive} and returns a reference to this Builder enabling method chaining.
     *
     * @param val the {@code isActive} to set
     * @return a reference to this Builder
     */
    public Builder isActive(boolean val) {
      isActive = val;
      return this;
    }

    /**
     * Returns a {@code Restaurant} built from the parameters previously set.
     *
     * @return a {@code Restaurant} built with parameters of this {@code Restaurant.Builder}
     */
    public Restaurant build() {
      return new Restaurant(this);
    }
  }
}
