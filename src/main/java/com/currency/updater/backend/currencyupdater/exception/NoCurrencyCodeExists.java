package com.currency.updater.backend.currencyupdater.exception;
/** Thrown if numeric code cannot be mapped for value */
public class NoCurrencyCodeExists extends BaseException {

  public NoCurrencyCodeExists(int value) {
    super("Currency.cannot.be.converted", value);
  }
}
