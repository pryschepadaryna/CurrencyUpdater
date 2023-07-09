package com.currency.updater.backend.currencyupdater.configuration;

import com.currency.updater.backend.currencyupdater.job.CurrencyRateUpdateJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/** Configuration for quartz scheduler for job {@see CurrencyRateUpdateJob} */
@Configuration
@Profile("!test")
public class QuartzConfig {

  final ApplicationContext applicationContext;

  public QuartzConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  /**
   * Creates trigger for job executing At second :00, every 5 minutes starting at minute :00, of
   * every hour
   */
  @Bean
  public CronTriggerFactoryBean createCronTriggerFactoryBean(JobDetail jobDetail) {
    CronTriggerFactoryBean simpleTriggerFactory = new CronTriggerFactoryBean();

    simpleTriggerFactory.setJobDetail(jobDetail);
    simpleTriggerFactory.setCronExpression("0 0/5 * * * ?");
    return simpleTriggerFactory;
  }

  @Bean
  public JobDetailFactoryBean createJobDetailFactoryBean() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(CurrencyRateUpdateJob.class);
    return jobDetailFactory;
  }

  @Bean
  SpringBeanJobFactory createSpringBeanJobFactory() {
    return new SpringBeanJobFactory() {

      @Override
      protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {

        final Object job = super.createJobInstance(bundle);

        applicationContext.getAutowireCapableBeanFactory().autowireBean(job);

        return job;
      }
    };
  }

  @Bean
  public SchedulerFactoryBean createSchedulerFactory(
      SpringBeanJobFactory springBeanJobFactory, Trigger trigger) {
    SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
    schedulerFactory.setAutoStartup(true);
    schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
    schedulerFactory.setTriggers(trigger);

    springBeanJobFactory.setApplicationContext(applicationContext);
    schedulerFactory.setJobFactory(springBeanJobFactory);

    return schedulerFactory;
  }
}
