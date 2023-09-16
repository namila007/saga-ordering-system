package me.namila.food_ordering.order.domain.core.valueobject;

import java.util.UUID;

import me.namila.food_ordering.common.valueobject.BaseId;

public class TrackingId extends BaseId<UUID> {
  public TrackingId(UUID baseId) {
    super(baseId);
  }
}
