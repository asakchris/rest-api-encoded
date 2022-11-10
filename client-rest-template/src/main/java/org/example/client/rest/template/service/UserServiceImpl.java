package org.example.client.rest.template.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.rest.template.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private static final ParameterizedTypeReference<List<User>> USER_LIST_TYPE = new ParameterizedTypeReference<>() {
  };
  private final RestTemplate restTemplate;
  @Value("${app.user-service.url}")
  private final String userServiceUrl;

  public List<User> getUsers() {
    return restTemplate.exchange(userServiceUrl,
        HttpMethod.GET, null, USER_LIST_TYPE).getBody();

  }
}
