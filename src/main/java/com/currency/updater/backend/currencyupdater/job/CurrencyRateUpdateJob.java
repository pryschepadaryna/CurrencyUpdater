package com.currency.updater.backend.currencyupdater.job;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.service.CurrencyRateService;
import com.currency.updater.backend.currencyupdater.service.ExternalApiAdapter;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/** Job to update currency rate based on external api response */
@Component
@Profile("!test")
public class CurrencyRateUpdateJob implements Job {

  private static final Logger LOG = LoggerFactory.getLogger(CurrencyRateUpdateJob.class);
  private final ExternalApiAdapter externalApiAdapter;
  private final CurrencyRateService currencyRateService;

  @Autowired
  public CurrencyRateUpdateJob(
      ExternalApiAdapter externalApiAdapter, CurrencyRateService currencyRateService) {
    this.externalApiAdapter = externalApiAdapter;
    this.currencyRateService = currencyRateService;
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("Currency Rate Update Job");
    List<CurrencyRateResponse> responses = externalApiAdapter.call();
    currencyRateService.updateAll(responses);
    stopWatch.stop();
    LOG.debug("Job has been finished, execution time report: {}", stopWatch.prettyPrint());
  }
}
