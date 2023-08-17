package me.namila.food_ordering.domain.application.ports.output.repository;

import java.util.Optional;

import me.namila.food_ordering.domain.core.entity.Restaurant;

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
