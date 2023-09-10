package me.namila.food_ordering.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * The type Kafka consumer config.
 */
@Data
@ConfigurationProperties(prefix = "kafka.consumer.config")
public class KafkaConsumerConfigData {
  private String keyDeserializer;
  private String valueDeserializer;
  private String autoOffsetReset;
  private String specificAvroReaderKey;
  private String specificAvroReader;
  private Boolean batchListener;
  private Boolean autoStartup;
  private Integer concurrencyLevel;
  private Integer sessionTimeoutMs;
  private Integer heartbeatIntervalMs;
  private Integer maxPollIntervalMs;
  private Long pollTimeoutMs;
  private Integer maxPollRecords;
  private Integer maxPartitionFetchBytesDefault;
  private Integer maxPartitionFetchBytesBoostFactor;
}
