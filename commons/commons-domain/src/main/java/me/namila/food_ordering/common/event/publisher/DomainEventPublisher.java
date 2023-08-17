package me.namila.food_ordering.common.event.publisher;

import me.namila.food_ordering.common.event.DomainEvent;

/**
 * The interface Domain event publisher.
 *
 * @param <T> the type parameter
 */
public interface DomainEventPublisher<T extends DomainEvent> {
    /**
     * Publish.
     *
     * @param domainEvent the domain event
     */
    void publish(T domainEvent);
}
