package com.currency.updater.backend.currencyupdater.exception;

/** Thrown if connect to {@code requestUrl} timed out. */
public class ConnectTimeoutException extends BaseException {

  public ConnectTimeoutException(String requestUrl, Throwable cause) {
    super("Destination.endpoint.connect.timeout", cause, requestUrl);
  }
}
