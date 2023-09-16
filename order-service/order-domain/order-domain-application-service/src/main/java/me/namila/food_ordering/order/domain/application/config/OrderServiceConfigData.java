package me.namila.food_ordering.order.domain.application.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfigData extends Properties {
  // private org.springframework.core.env.Environment delegate;
  //
  // @Override
  // public String getProperty(String key) {
  // return delegate.getProperty(key);
  // }
  //
  // @Override
  // public String getProperty(String key, String defaultValue) {
  // return delegate.getProperty(key, defaultValue);
  // }

}
