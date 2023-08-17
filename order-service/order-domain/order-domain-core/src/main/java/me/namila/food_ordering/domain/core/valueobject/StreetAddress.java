package me.namila.food_ordering.domain.core.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The type Street address.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class StreetAddress {
    @EqualsAndHashCode.Exclude
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;
}
