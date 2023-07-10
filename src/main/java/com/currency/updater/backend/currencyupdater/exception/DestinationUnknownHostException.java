package com.currency.updater.backend.currencyupdater.exception;

import lombok.NonNull;

/** Thrown if not resolve hostname of {@code requestUrl}. */
public class DestinationUnknownHostException extends BaseException {

  public DestinationUnknownHostException(@NonNull Throwable cause, String requestUrl) {
    super("Destination.endpoint.connect.unknown.host", cause, requestUrl);
  }
}
