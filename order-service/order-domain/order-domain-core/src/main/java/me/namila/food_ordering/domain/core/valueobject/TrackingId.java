package me.namila.food_ordering.domain.core.valueobject;

import me.namila.food_ordering.common.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
  public TrackingId(UUID baseId) {
    super(baseId);
  }
}
