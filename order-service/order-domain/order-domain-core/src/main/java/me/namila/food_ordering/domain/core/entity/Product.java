package me.namila.food_ordering.domain.core.entity;

import lombok.Getter;
import me.namila.food_ordering.common.entity.BaseEntity;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.ProductId;

/**
 * The type Product.
 */
@Getter
public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money money;

  public Product(ProductId productId, String name, Money money) {
    super.setId(productId);
    this.name = name;
    this.money = money;
  }

  public Product(ProductId productId) {
    super.setId(productId);
  }

  public void updateWithConfirmedValues(Product product) {
    this.name = product.getName();
    this.money = product.getMoney();
  }
}
