package me.namila.food_ordering.order.dataaccess.restaurant.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import lombok.NonNull;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.ProductId;
import me.namila.food_ordering.common.valueobject.RestaurantId;
import me.namila.food_ordering.order.dataaccess.restaurant.entity.RestaurantEntity;
import me.namila.food_ordering.order.dataaccess.restaurant.entity.key.RestaurantEntityId;
import me.namila.food_ordering.order.domain.core.entity.Product;
import me.namila.food_ordering.order.domain.core.entity.Restaurant;

/**
 * The type Restaurant data access mapper.
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class RestaurantDataAccessMapper {

  /**
   * To restaurant restaurant.
   *
   * @param restaurantEntities the restaurant entities
   * @return the restaurant
   */
  // @Mapping(target = "isActive", expression = "java(isActiveRestaurant(restaurantEntities))")
  // @Mapping(target = "products", expression = "java(getRestaurantProduct(restaurantEntities))")
  // @Mapping(target = "id.baseId", expression = "java(getRestaurantId(restaurantEntities))")
  public Restaurant toRestaurant(List<RestaurantEntity> restaurantEntities) {
    return Restaurant.Builder.builder().isActive(isActiveRestaurant(restaurantEntities))
        .products(getRestaurantProduct(restaurantEntities))
        .restaurantId(getRestaurantId(restaurantEntities)).build();
  }


  private RestaurantId getRestaurantId(@NonNull List<RestaurantEntity> restaurantEntity) {
    return restaurantEntity.stream()
        .map(e -> new RestaurantId(e.getRestaurantEntityId().getRestaurantId())).findFirst()
        .orElse(null);
  }

  private boolean isActiveRestaurant(@NonNull List<RestaurantEntity> restaurantEntity) {
    return restaurantEntity.stream().allMatch(e -> e.getRestaurantActive());
  }

  /**
   * Gets restaurant product.
   *
   * @param restaurantEntities the restaurant entities
   * @return the restaurant product
   */
  protected List<Product> getRestaurantProduct(List<RestaurantEntity> restaurantEntities) {
    return restaurantEntities.stream()
        .map(restaurantEntity -> new Product(
            new ProductId(restaurantEntity.getRestaurantEntityId().getProductId()),
            restaurantEntity.getProductName(), new Money(restaurantEntity.getProductPrice())))
        .collect(Collectors.toList());
  }

  /**
   * Restaurant to restaurant entity ids list.
   *
   * @param restaurant the restaurant
   * @return the list
   */
  public List<RestaurantEntityId> restaurantToRestaurantEntityIds(@NonNull Restaurant restaurant) {
    return Objects.isNull(restaurant.getProducts()) ? new ArrayList<>()
        : restaurant.getProducts().stream()
            .map(
                product -> RestaurantEntityId.builder().restaurantId(restaurant.getId().getBaseId())
                    .productId(product.getId().getBaseId()).build())
            .collect(Collectors.toList());
  }


  public List<RestaurantEntity> toRestaurantEntities(Restaurant restaurant) {
    Boolean isActive = restaurant.isActive();
    UUID id = restaurant.getId().getBaseId();
    return restaurant.getProducts().stream()
        .map(prod -> RestaurantEntity.builder()
            .restaurantEntityId(RestaurantEntityId.builder().restaurantId(id)
                .productId(prod.getId().getBaseId()).build())
            .restaurantActive(isActive).productName(prod.getName())
            .productPrice(prod.getMoney().getAmount()).build())
        .collect(Collectors.toList());

  }
}
