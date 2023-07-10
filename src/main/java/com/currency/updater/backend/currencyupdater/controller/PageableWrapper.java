package com.currency.updater.backend.currencyupdater.controller;

import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.domain.Page;

@Value
@Builder
@FieldNameConstants
public class PageableWrapper<T> {

  Integer totalPages;

  Long totalElements;

  List<T> entities;

  /**
   * Factory method that creates instance of {@link PageableWrapper} from {@link Page}.
   *
   * @param data content wrapped in {@link Page}
   * @param <T> the type of content
   * @return new instance of {@link PageableWrapper}
   * @throws IllegalArgumentException if {@code data} is {@code null}
   */
  public static <T> PageableWrapper<T> of(@NonNull Page<T> data) {
    return PageableWrapper.<T>builder()
        .totalElements(data.getTotalElements())
        .totalPages(data.getTotalPages())
        .entities(data.getContent())
        .build();
  }
}
