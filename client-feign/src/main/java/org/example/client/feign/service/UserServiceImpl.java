package org.example.client.feign.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.feign.client.UserClient;
import org.example.client.feign.model.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserClient client;

  public List<User> getUsers() {
    return client.getUsers();
  }
}
