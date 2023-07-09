package com.currency.updater.backend.currencyupdater.dto;

import com.currency.updater.backend.currencyupdater.exception.NoCurrencyCodeExists;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/** Received external API response */
@Getter
@Setter
@ToString
@FieldNameConstants
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateResponse {

  /** Currency code according to ISO 4217 */
  String currencyCodeA;

  /** Currency code according to ISO 4217 */
  String currencyCodeB;

  /** Date of rate update */
  Long date;

  /** Rate for sell */
  Double rateSell;
  /** Rate for buy */
  Double rateBuy;
  /** Cross rate */
  Double rateCross;

  /** Converts numeric code to ISO 4217 code */
  public void setCurrencyCodeA(int currencyNumberA) {
    Currency currencyA =
        Currency.getAvailableCurrencies().stream()
            .filter(currency -> currency.getNumericCode() == (currencyNumberA))
            .findFirst()
            .orElseThrow(() -> new NoCurrencyCodeExists(currencyNumberA));
    this.currencyCodeA = currencyA.getCurrencyCode();
  }

  /** Converts numeric code to ISO 4217 code */
  public void setCurrencyCodeB(int currencyNumberB) {
    Currency currencyB =
        Currency.getAvailableCurrencies().stream()
            .filter(currency -> currency.getNumericCode() == (currencyNumberB))
            .findFirst()
            .orElseThrow(() -> new NoCurrencyCodeExists(currencyNumberB));
    this.currencyCodeB = currencyB.getCurrencyCode();
  }
}
