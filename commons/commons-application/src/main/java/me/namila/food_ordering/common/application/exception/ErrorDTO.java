package me.namila.food_ordering.common.application.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Error dto.
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {
  private String httpStatus;
  private String message;
}
