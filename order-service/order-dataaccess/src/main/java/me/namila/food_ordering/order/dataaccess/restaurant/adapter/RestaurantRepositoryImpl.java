package me.namila.food_ordering.order.dataaccess.restaurant.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.order.dataaccess.restaurant.entity.RestaurantEntity;
import me.namila.food_ordering.order.dataaccess.restaurant.entity.key.RestaurantEntityId;
import me.namila.food_ordering.order.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import me.namila.food_ordering.order.dataaccess.restaurant.repository.RestaurantJpaRepository;
import me.namila.food_ordering.order.domain.application.ports.output.repository.RestaurantRepository;
import me.namila.food_ordering.order.domain.core.entity.Restaurant;

/**
 * The type Restaurant repository.
 */
@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
  private final RestaurantJpaRepository restaurantJpaRepository;
  private final RestaurantDataAccessMapper restaurantDataAccessMapper;

  /**
   * Instantiates a new Restaurant repository.
   *
   * @param restaurantJpaRepository the restaurant jpa repository
   * @param restaurantDataAccessMapper the restaurant data access mapper
   */
  public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
      RestaurantDataAccessMapper restaurantDataAccessMapper) {
    this.restaurantJpaRepository = restaurantJpaRepository;
    this.restaurantDataAccessMapper = restaurantDataAccessMapper;
  }

  @Override
  public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
    List<RestaurantEntityId> restaurantEntityIdList =
        restaurantDataAccessMapper.restaurantToRestaurantEntityIds(restaurant);
    Optional<List<RestaurantEntity>> restaurantEntityList =
        restaurantJpaRepository.findByRestaurantEntityIdIn(restaurantEntityIdList);
    return restaurantEntityList.map(restaurantDataAccessMapper::toRestaurant);
  }
}
