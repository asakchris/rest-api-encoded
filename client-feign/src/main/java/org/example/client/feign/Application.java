package org.example.client.feign;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.feign.model.User;
import org.example.client.feign.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
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
