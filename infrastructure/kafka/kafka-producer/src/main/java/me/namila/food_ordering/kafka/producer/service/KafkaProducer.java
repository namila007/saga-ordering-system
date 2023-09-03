package me.namila.food_ordering.kafka.producer.service;

import java.io.Serializable;
import java.util.function.BiConsumer;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;

/**
 * The interface Kafka producer.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {

    /**
     * Send.
     *
     * @param topicName the topic name
     * @param key the key
     * @param value the value
     * @param action the action
     */
    void send(String topicName, K key, V value, BiConsumer<? super SendResult<K,V>, ? super Throwable> action);
}
