package me.namila.food_ordering.payment.domain.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The type Payment service config data.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData extends Properties {
  private String paymentRequestTopicName;
  private String paymentResponseTopicName;
}
