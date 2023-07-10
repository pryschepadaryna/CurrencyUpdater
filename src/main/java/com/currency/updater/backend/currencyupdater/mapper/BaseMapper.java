package com.currency.updater.backend.currencyupdater.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/** Base mapper for entities */
public interface BaseMapper {

  default LocalDateTime map(Long date) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), TimeZone.getDefault().toZoneId());
  }

  default Long map(LocalDateTime date) {
    return date.toInstant(ZoneOffset.UTC).toEpochMilli();
  }
}
