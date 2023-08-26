package me.namila.food_ordering.kafka.producer.service;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.kafka.producer.exception.KafkaProducerException;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * The type Kafka producer.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    /**
     * Instantiates a new Kafka producer.
     *
     * @param kafkaTemplate the kafka template
     */
    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, BiConsumer<? super SendResult<K, V>, ? super Throwable> action) {
        log.info("KafkaProducerImpl::send -  Sending message: {} to topic: {}", topicName, message);
        try {
            CompletableFuture<SendResult<K, V>> kafkaCompletableFuture = kafkaTemplate.send(topicName, key, message);
            kafkaCompletableFuture.whenComplete(action);
        } catch (KafkaException kafkaException) {
            log.error("KafkaProducerImpl::send - error occurred with key: {} , message: {} , exception: {}", key,
                    message, kafkaException.getMessage(), kafkaException);
            throw new KafkaProducerException("Error occurred for key: " + key + " and message: " + message);
        }
    }

    /**
     * Destroy.
     */
    @PreDestroy
    public void destroy() {
        if (kafkaTemplate != null) kafkaTemplate.destroy();
    }
}
