package com.currency.updater.backend.currencyupdater.dto;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@Builder
@FieldNameConstants
public class GetCurrencyRateDto {
  Long id;
  Long latestExternalUpdate;
  String nameA;
  String nameB;
  Double rateToBuy;
  Double rateToSell;
  Double crossRate;
}
