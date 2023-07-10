package com.currency.updater.backend.currencyupdater.mapper;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateFilterDto;
import com.currency.updater.backend.currencyupdater.filter.CurrencyRateFilter;
import org.mapstruct.Mapper;

/** Mapper for {@link CurrencyRateFilter} */
@Mapper(componentModel = "spring")
public interface CurrencyRateFilterMapper extends BaseMapper {

  CurrencyRateFilter map(CurrencyRateFilterDto dto);
}
