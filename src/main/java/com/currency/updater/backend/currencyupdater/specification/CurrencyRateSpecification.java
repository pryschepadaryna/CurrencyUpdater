package com.currency.updater.backend.currencyupdater.specification;

import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.filter.CurrencyRateFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/** JPA Specification to filter currency rates */
public class CurrencyRateSpecification {

  CurrencyRateFilter filter;

  private CurrencyRateSpecification() {}

  public static CurrencyRateSpecification create() {
    return new CurrencyRateSpecification();
  }

  public CurrencyRateSpecification filter(final CurrencyRateFilter filter) {
    this.filter = filter;
    return this;
  }

  public Specification<CurrencyRate> build() {
    return (root, query, builder) -> {
      final List<Predicate> predicates = new ArrayList<>();
      buildCurrencyRateFilter(root, builder, predicates);
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }

  private void buildCurrencyRateFilter(
      final Root<CurrencyRate> root,
      final CriteriaBuilder builder,
      final List<Predicate> predicates) {
    if (filter == null) {
      return;
    }
    eqCaseInsensitive(root.get(CurrencyRate.Fields.nameA), filter.getNameA(), predicates, builder);

    eqCaseInsensitive(root.get(CurrencyRate.Fields.nameB), filter.getNameB(), predicates, builder);

    if (CollectionUtils.isNotEmpty(filter.getIds())) {
      in(root.get(CurrencyRate.Fields.id), predicates, filter.getIds().toArray());
    }

    time(
        root.get(CurrencyRate.Fields.latestExternalUpdate),
        filter.getLatestExternalUpdateFrom(),
        filter.getLatestExternalUpdateTo(),
        predicates,
        builder);
  }

  protected static void in(
      final Path<Object> valuePath, final List<Predicate> predicates, final Object... values) {
    if (ArrayUtils.isNotEmpty(values)) {
      predicates.add(valuePath.in(values));
    }
  }

  protected static void time(
      final Expression<LocalDateTime> root,
      final LocalDateTime start,
      final LocalDateTime end,
      final List<Predicate> predicates,
      final CriteriaBuilder builder) {
    if (start != null && end == null) {
      predicates.add(builder.greaterThanOrEqualTo(root, start));
    }
    if (start == null && end != null) {
      predicates.add(builder.lessThanOrEqualTo(root, end));
    }
    between(root, start, end, predicates, builder);
  }

  protected static void between(
      final Expression<LocalDateTime> root,
      final LocalDateTime start,
      final LocalDateTime end,
      final List<Predicate> predicates,
      final CriteriaBuilder builder) {
    if (start != null && end != null) {
      predicates.add(builder.between(root, start, end));
    }
  }

  protected static <T> void eq(
      final Path<T> valuePath,
      final T value,
      final List<Predicate> predicates,
      final CriteriaBuilder cb) {
    if (value != null) {
      predicates.add(cb.equal(valuePath, value));
    }
  }

  protected static void eqCaseInsensitive(
      final Path<String> valuePath,
      final String value,
      final List<Predicate> predicates,
      final CriteriaBuilder cb) {
    if (StringUtils.isNotBlank(value)) {
      predicates.add(cb.equal(cb.lower(valuePath), value.toLowerCase()));
    }
  }
}
