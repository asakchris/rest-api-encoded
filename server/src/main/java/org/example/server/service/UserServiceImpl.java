package org.example.server.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.server.entity.User;
import org.example.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  @Transactional(readOnly = true)
  public List<User> findAllUsers() {
    return repository.findAll();
  }
}
