package com.example.springsecuritywithangular.transformer;

import com.example.springsecuritywithangular.dto.SignupRequestDto;
import com.example.springsecuritywithangular.entities.Customer;

public class CustomerTransformer {

    public static Customer SignupRequestDtoToCustomer(SignupRequestDto signupRequestDto){
        return Customer.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .build();
    }
}
