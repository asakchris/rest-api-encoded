package org.example.client.rest.template.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.client.rest.template.model.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final RestTemplate restTemplate;

  public List<User> getUsers() {
    return restTemplate.exchange("http://localhost:18000/api/v1/server/users",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();

  }
}
