package me.namila.food_ordering.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * The type Kafka producer config.
 */
@Data
@ConfigurationProperties(prefix = "kafka.producer.config")
public class KafkaProducerConfigData {
  private String keySerializerClass;
  private String valueSerializerClass;
  private String compressionType;
  private String acks;
  private Integer batchSize;
  private Integer batchSizeBoostFactor;
  private Integer lingerMs;
  private Integer requestTimeoutMs;
  private Integer retryCount;
}
