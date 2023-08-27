package me.namila.food_ordering.kafka.consumer.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

/**
 * The interface Kafka consumer.
 *
 * @param <T> the type parameter
 */
public interface KafkaConsumer<T extends SpecificRecordBase> {
    /**
     * Receive.
     *
     * @param messages  the messages
     * @param keys      the keys
     * @param partition the partition
     * @param offsets   the offsets
     */
    void receive(List<T> messages, List<Long> keys, List<Integer> partition, List<Long> offsets);
}
