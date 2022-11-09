package org.example.client.feign.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
public class ApacheClientConfig {

  // Spring Cloud DefaultApacheHttpClientFactory, by default, disables
  // content compression in Apache Http Builder which would not register appropriate Stream handler for GZIP
  @Bean
  public ApacheHttpClientFactory apacheHttpClientFactory(HttpClientBuilder builder) {
    return () -> builder.disableCookieManagement().useSystemProperties();
  }
}
