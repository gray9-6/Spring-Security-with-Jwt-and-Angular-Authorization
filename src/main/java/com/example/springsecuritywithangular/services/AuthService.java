package com.example.springsecuritywithangular.services;

import com.example.springsecuritywithangular.dto.SignupRequestDto;

public interface AuthService {

    boolean createCustomer(SignupRequestDto signupRequestDto);
}
