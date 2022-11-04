package org.example.client.feign.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
  private Long userId;

  private String firstName;

  private String lastName;

  private String email;

  private String gender;

  private String ipAddress;
}
