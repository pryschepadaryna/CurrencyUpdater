package com.currency.updater.backend.currencyupdater.service;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/** Used to for connection with external API to get updated currency rate */
@Component
public class ExternalApiAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(ExternalApiAdapter.class);

  private final Duration requestTimeout;

  private final String myUri;
  private final WebClient webClient;

  @Autowired
  public ExternalApiAdapter(
      @Value("${mvc.request-timeout}") Duration requestTimeout,
      @Value("${external-api.url}") String url,
      WebClient.Builder webClientBuilder) {
    this.requestTimeout = requestTimeout;
    this.myUri = url;
    this.webClient = webClientBuilder.baseUrl(myUri).build();
  }

  public List<CurrencyRateResponse> call() {
    LOG.info("Calling of external API was started, time[{}]", LocalDateTime.now());
    return webClient
        .get()
        .uri(myUri)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<CurrencyRateResponse>>() {})
        .blockOptional(requestTimeout)
        .orElseGet(ArrayList::new);
  }
}
