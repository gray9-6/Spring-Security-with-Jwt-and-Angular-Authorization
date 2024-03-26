package com.example.springsecuritywithangular.controllers;

import com.example.springsecuritywithangular.dto.SignupRequestDto;
import com.example.springsecuritywithangular.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final AuthService authService;


    @PostMapping
    public ResponseEntity<String> signupCustomer(@RequestBody SignupRequestDto signupRequestDto){
        boolean isUserCreated = authService.createCustomer(signupRequestDto);
        if(isUserCreated){
            return new ResponseEntity<>("Customer Created Successfully", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Failed to create the customer",HttpStatus.BAD_REQUEST);
        }
    }
}
