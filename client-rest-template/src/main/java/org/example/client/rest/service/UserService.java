package org.example.client.rest.service;

import java.util.List;
import org.example.client.rest.model.User;

public interface UserService {
  List<User> getUsers();
}
