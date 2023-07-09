package com.currency.updater.backend.currencyupdater.exception;

import lombok.NonNull;

/** Thrown if entity cannot be found. */
public class EntityNotFoundException extends BaseException {
  public EntityNotFoundException(Object key, @NonNull Class<?> klass) {
    super("Entity.not.found", klass.getSimpleName(), key);
  }
}
