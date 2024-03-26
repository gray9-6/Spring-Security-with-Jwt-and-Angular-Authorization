package com.example.springsecuritywithangular.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequestDto {

    String name;
    String password;
    String email;
}
