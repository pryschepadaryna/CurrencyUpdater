package com.currency.updater.backend.currencyupdater.factory;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrencyRateResponseObjectMother {

  public static List<CurrencyRateResponse> createDefaultCurrencyRate(String path)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return Arrays.stream(objectMapper.readValue(new File(path), CurrencyRateResponse[].class))
        .toList();
  }
}
