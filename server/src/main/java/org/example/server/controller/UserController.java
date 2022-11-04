package org.example.server.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.entity.User;
import org.example.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService service;

  @GetMapping(value = "/users")
  @ResponseStatus(code = HttpStatus.OK)
  public List<User> findAllUsers() {
    log.info("Enter findAllUsers");
    return service.findAllUsers();
  }
}
