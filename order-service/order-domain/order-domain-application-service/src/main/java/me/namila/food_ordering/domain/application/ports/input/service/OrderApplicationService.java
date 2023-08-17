package me.namila.food_ordering.domain.application.ports.input.service;

import jakarta.validation.Valid;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderQuery;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderResponse;

/**
 * The interface Order application service.
 */
public interface OrderApplicationService {
  /**
   * Create order response.
   *
   * @param createOrderCommand the create order command
   * @return the create order response
   */
  CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

  /**
   * Track order response.
   *
   * @param trackOrderQuery the track order query
   * @return the track order response
   */
  TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
