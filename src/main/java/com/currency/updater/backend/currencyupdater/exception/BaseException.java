package com.currency.updater.backend.currencyupdater.exception;

import static com.currency.updater.backend.currencyupdater.CurrencyUpdaterConstants.DEFAULT_MESSAGES_RESOURCE_BUNDLE;

import java.util.Arrays;
import org.jetbrains.annotations.PropertyKey;

/**
 * Superclass of those exceptions that can be thrown during business logic rules validation.
 *
 * <p>{@link BaseException} and its subclasses are <em>unchecked exceptions</em><br>
 * In most cases {@code message} of {@link BaseException} should be code of corresponding error
 * message from message source.
 */
public class BaseException extends RuntimeException {

  private final transient Object[] args;

  public BaseException(
      @PropertyKey(resourceBundle = DEFAULT_MESSAGES_RESOURCE_BUNDLE) String message) {
    this(message, (Object[]) null);
  }

  public BaseException(
      @PropertyKey(resourceBundle = DEFAULT_MESSAGES_RESOURCE_BUNDLE) String message,
      Object... args) {
    super(message);
    this.args = makeCopy(args);
  }

  private Object[] makeCopy(Object[] args) {
    if (args == null || args.length == 0) {
      return args;
    }
    return Arrays.copyOf(args, args.length);
  }
}
