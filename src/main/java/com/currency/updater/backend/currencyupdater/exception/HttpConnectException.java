package com.currency.updater.backend.currencyupdater.exception;

import lombok.NonNull;

/** Signals that an error occurred while attempting to connect to a remote HTTP server. */
public class HttpConnectException extends RuntimeException {

  /** URI of remote HTTP server. */
  private final String uri;

  public HttpConnectException(@NonNull Throwable cause, String uri) {
    super(cause);
    this.uri = uri;
  }

  public String getUri() {
    return uri;
  }
}
