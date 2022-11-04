package org.example.client.feign.service;

import java.util.List;
import org.example.client.feign.model.User;

public interface UserService {
  List<User> getUsers();
}
