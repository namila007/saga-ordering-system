package me.namila.food_ordering.order.domain.application.ports.output.repository;

import java.util.Optional;

import me.namila.food_ordering.order.domain.core.entity.Restaurant;

/**
 * The interface Restaurant repository.
 */
public interface RestaurantRepository {
    /**
     * Find restaurant information optional.
     *
     * @param restaurant the restaurant
     * @return the optional
     */
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

}
