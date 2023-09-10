package me.namila.food_ordering.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * The type Kafka config.
 */
@Data
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaConfigData {
  private String bootstrapServers;
  private String schemaRegistryUrlKey;
  private String schemaRegistryUrl;
  private Integer numOfPartitions;
  private Short replicationFactor;
}
