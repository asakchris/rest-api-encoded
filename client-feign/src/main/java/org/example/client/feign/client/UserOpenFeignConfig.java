package org.example.client.feign.client;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Request;
import feign.Response;
import feign.Response.Body;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserOpenFeignConfig {
  @Bean
  public Retryer retryer() {
    return new Retryer.Default(SECONDS.toMillis(2L), SECONDS.toMillis(10L), 4);
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }

  @Slf4j
  static class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

      switch (response.status()) {
        case 502:
        case 503:
        case 504:
        {
          if (log.isInfoEnabled()) {
            log.info(
                "Retrying: request={}, statusCode={}, methodKey={}, reason={}",
                new String(
                    Optional.ofNullable(response.request())
                        .map(Request::body)
                        .orElse(new byte[0])),
                response.status(),
                methodKey,
                response.reason());
          }
          return new RetryableException(
              response.status(),
              response.reason(),
              response.request().httpMethod(),
              null,
              response.request());
        }
        default:
          String failureReason =
              Optional.ofNullable(response.body()).map(this::extractResponseFailureMsg).orElse("");

          if (log.isInfoEnabled()) {
            log.info(
                "OpenFeign T3 Client RESPONSE_STATUS: [{}] and RESPONSE_MESSAGE: [{}]",
                HttpStatus.valueOf(response.status()),
                failureReason);
          }
          return new ResponseStatusException(
              HttpStatus.valueOf(response.status()), failureReason);
      }
    }

    private String extractResponseFailureMsg(Body responseBody) {
      StringBuilder failureMessage = new StringBuilder();
      try (BufferedReader reader =
          new BufferedReader(responseBody.asReader(StandardCharsets.UTF_8))) {
        failureMessage.append(reader.lines().collect(Collectors.joining()));
        return failureMessage.toString();
      } catch (IOException e) {
        if (log.isInfoEnabled()) {
          log.info(
              "Exception occurred while reading error response stream : [{}]", e.getMessage());
        }
        return "";
      }
    }
  }
}
