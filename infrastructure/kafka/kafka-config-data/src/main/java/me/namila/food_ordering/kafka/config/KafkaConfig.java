package me.namila.food_ordering.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Kafka config.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaConfig {
    private String bootstrapServer;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private Integer numOfPartitions;
    private Short replicationFactor;
}
