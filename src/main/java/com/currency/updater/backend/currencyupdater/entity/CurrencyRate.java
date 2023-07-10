package com.currency.updater.backend.currencyupdater.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.LastModifiedDate;

/** This entity represents current currency rate received from external API */
@Entity
@Data
@FieldNameConstants
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {
  @ToString.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "updated_at", columnDefinition = "DATETIME")
  @LastModifiedDate
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDateTime updatedAt;

  /** Update time of rate received from external API */
  private LocalDateTime latestExternalUpdate;

  /** Currency name to buy */
  private String nameA;

  /** Currency name to sell */
  private String nameB;

  /** Currency rate to buy */
  private Double rateToBuy;
  /** Currency rate to sell */
  private Double rateToSell;
  /** Currency cross rate */
  private Double crossRate;
}
