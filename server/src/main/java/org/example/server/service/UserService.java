package org.example.server.service;

import java.util.List;
import org.example.server.entity.User;

public interface UserService {
  List<User> findAllUsers();
}
