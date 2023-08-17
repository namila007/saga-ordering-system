package me.namila.food_ordering.domain.application.ports.input.service;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderQuery;
import me.namila.food_ordering.domain.application.dto.track.TrackOrderResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery){

    }
}
