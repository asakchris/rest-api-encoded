package org.example.client.feign.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

  // Spring Cloud DefaultApacheHttpClientFactory, by default, disables
  // content compression in Apache Http Builder which would not register appropriate Stream handler for GZIP
  @Bean
  @ConditionalOnMissingBean
  public ApacheHttpClientFactory apacheHttpClientFactory(HttpClientBuilder builder) {
    return () -> builder.disableCookieManagement().useSystemProperties();
  }
}
