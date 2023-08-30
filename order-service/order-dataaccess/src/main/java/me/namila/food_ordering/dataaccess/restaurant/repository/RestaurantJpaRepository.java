package me.namila.food_ordering.dataaccess.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.namila.food_ordering.dataaccess.restaurant.entity.RestaurantEntity;
import me.namila.food_ordering.dataaccess.restaurant.entity.key.RestaurantEntityId;

import java.util.List;
import java.util.Optional;

/**
 * The interface Restaurant jpa repository.
 */
@Repository
public interface RestaurantJpaRepository
    extends JpaRepository<RestaurantEntity, RestaurantEntityId> {

  Optional<List<RestaurantEntity>> findByRestaurantEntityId(
      List<RestaurantEntityId> restaurantEntityId);
}
