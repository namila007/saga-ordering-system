package me.namila.food_ordering.kafka.producer.exception;

/**
 * The type Kafka producer exception.
 */
public class KafkaProducerException extends RuntimeException {
    /**
     * Instantiates a new Kafka producer exception.
     *
     * @param message the message
     */
    public KafkaProducerException(String message) {
        super(message);
    }
}
