package org.example.client.rest.template;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.client.rest.template.model.User;
import org.example.client.rest.template.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
  private final UserService service;

  @Override
  public void run(String... args) throws Exception {
    log.info("Started");
    final List<User> users = service.getUsers();
    log.info("users size: {}", users.size());
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
