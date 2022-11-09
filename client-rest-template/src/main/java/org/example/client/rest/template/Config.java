package org.example.client.rest.template;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

  @Bean
  RestTemplate restTemplate(RestTemplateBuilderConfigurer restTemplateBuilderConfigurer) {

    RestTemplateBuilder builder = new RestTemplateBuilder()
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
            .useSystemProperties()
            .disableCookieManagement()
            .build()));
    restTemplateBuilderConfigurer.configure(builder);

    return builder.build();
  }
}
