package me.namila.food_ordering.application.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.application.exception.OrderApplicationException;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderCommand;
import me.namila.food_ordering.domain.application.dto.create.CreateOrderResponse;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderQuery;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderResponse;
import me.namila.food_ordering.domain.application.service.OrderApplicationService;

/**
 * The type Order controller.
 */
@Slf4j
@RestController
@RequestMapping("/v1/order")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  /**
   * Instantiates a new Order controller.
   *
   * @param orderApplicationService the order application service
   */
  public OrderController(OrderApplicationService orderApplicationService) {
    this.orderApplicationService = orderApplicationService;
  }


  /**
   * Create order response entity.
   *
   * @param createOrderCommand the create order command
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<CreateOrderResponse> createOrder(
      @RequestBody CreateOrderCommand createOrderCommand) {
    log.info("OrderController::createOrder - creating order for customer: {}, restaurantId: {}",
        createOrderCommand.getCustomerId(), createOrderCommand.getRestaurantId());
    var result = orderApplicationService.createOrder(createOrderCommand);
    log.info("OrderController::createOrder - created order for customer: {} , trackId: {}",
        createOrderCommand.getCustomerId(), result.getOrderTrackingId());
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  /**
   * Track order by track id response entity.
   *
   * @param trackingId the tracking id
   * @return the response entity
   */
  @GetMapping("/{trackingId}")
  public ResponseEntity<TrackOrderResponse> trackOrderByTrackId(@PathVariable String trackingId) {
    UUID trackUUID = null;
    try {
      trackUUID = UUID.fromString(trackingId);
    } catch (IllegalArgumentException e) {
      log.error("OrderController::getOrderByTrackId - not a valid UUID: {}", trackingId);
      throw new OrderApplicationException("not a valid trackingId: " + trackingId);
    }
    log.info("OrderController::getOrderByTrackId - trackId: {}", trackingId);
    var result = orderApplicationService
        .trackOrder(TrackOrderQuery.builder().orderTracking(trackUUID).build());
    log.info("OrderController::getOrderByTrackId - order found, trackId: {}",
        result.getOrderTrackingId());
    return ResponseEntity.ok(result);
  }
}
