package com.currency.updater.backend.currencyupdater.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * Container class for HTTP response.
 *
 * @param <T> type of response payload
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public final class GeneralResponse<T> {

  public static final String DATA_FIELD_NAME = "data";
  public static final String MESSAGE_FIELD_NAME = "message";
  public static final String CODE_FIELD_NAME = "code";

  /** Payload of response. */
  private final T data;

  /** Optional message. */
  private final String message;

  /** HTTP status code of response. */
  private final int code;

  public static <T> GeneralResponse<T> ok(T body) {
    return new GeneralResponse<>(body, null, HttpStatus.OK.value());
  }
}
