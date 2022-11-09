package org.example.client.feign.client;

import java.util.List;
import org.example.client.feign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    value = "userClient",
    url = "${app.feign.user-service.url}",
    configuration = UserOpenFeignConfig.class)
public interface UserClient {
  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  List<User> getUsers();
}
