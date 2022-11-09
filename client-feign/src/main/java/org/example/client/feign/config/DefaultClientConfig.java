package org.example.client.feign.config;

import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.encoding.BaseRequestInterceptor;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.cloud.openfeign.encoding.HttpEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FeignClientEncodingProperties.class)
@ConditionalOnProperty(value = "feign.httpclient.enabled", havingValue = "false")
public class DefaultClientConfig {

  /**
   * When using default feign client, FeignAcceptGzipEncodingAutoConfiguration is not registered
   * So, we're adding our own Feign interceptor. Also, Default feign client is able to handle gzip encoding
   */
  @Bean
  public BaseRequestInterceptor feignAcceptGzipEncodingInterceptor(
      FeignClientEncodingProperties properties) {
    return new BaseRequestInterceptor(properties) {
      @Override
      public void apply(RequestTemplate template) {
        addHeader(template, HttpEncoding.ACCEPT_ENCODING_HEADER, HttpEncoding.GZIP_ENCODING,
            HttpEncoding.DEFLATE_ENCODING);
      }
    };
  }
}
