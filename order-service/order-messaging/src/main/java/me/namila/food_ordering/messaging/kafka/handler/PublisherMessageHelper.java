package me.namila.food_ordering.messaging.kafka.handler;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Publisher message helper.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
@Slf4j
@Component
public class PublisherMessageHelper<K, V> {

  /**
   * The Handle response of kafka publisher.
   */
  public Function<String, BiConsumer<? super SendResult<K, V>, ? super Throwable>> handleResponse =
      (id) -> (success, error) -> {
        if (error != null) {
          log.error(
              "PublisherMessageHelper::handleResponse -  error occurred while publishing Id: {} , msg: {}",
              id, error.getMessage(), error);
        } else {
          RecordMetadata recordMetadata = success.getRecordMetadata();
          log.info(
              "PublisherMessageHelper::handleResponse - Received successful response from Kafka for id: {}"
                  + " Topic: {} Partition: {} Offset: {} Timestamp: {}",
              id, recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(),
              recordMetadata.timestamp());
        }
      };
}
