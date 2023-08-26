package me.namila.food_ordering.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

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
     * @param key       the key
     * @param value     the value
     * @param callback  the callback
     */
    //ToDO ListenableFutureCallback -> CompletableFuture
    void send(String topicName, K key, V value, BiConsumer<? super SendResult<K,V>, ? super Throwable> action);
}
