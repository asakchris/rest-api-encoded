package org.example.client.rest.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.client.rest.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final RestTemplate template;

  @Override
  public List<User> getUsers() {
    final ResponseEntity<List<User>> response =
        template.exchange(
            "http://localhost:18000/api/v1/server/users",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {});
    return response.getBody();
  }
}
