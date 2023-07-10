package com.currency.updater.backend.currencyupdater.mapper;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.dto.GetCurrencyRateDto;
import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Mapper for {@link CurrencyRate} */
@Mapper(componentModel = "spring")
public interface CurrencyRateMapper extends BaseMapper {

  @Mapping(source = "date", target = "latestExternalUpdate")
  @Mapping(source = "currencyCodeA", target = "nameA")
  @Mapping(source = "currencyCodeB", target = "nameB")
  @Mapping(source = "rateBuy", target = "rateToBuy")
  @Mapping(source = "rateSell", target = "rateToSell")
  @Mapping(source = "rateCross", target = "crossRate")
  CurrencyRate map(CurrencyRateResponse dto);

  GetCurrencyRateDto map(CurrencyRate dto);
}
