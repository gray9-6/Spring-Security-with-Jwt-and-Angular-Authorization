package com.example.springsecuritywithangular.controllers;

import com.example.springsecuritywithangular.dto.LoginRequestDto;
import com.example.springsecuritywithangular.dto.LoginResponseDto;
import com.example.springsecuritywithangular.services.jwt.CustomerServiceImpl;
import com.example.springsecuritywithangular.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final CustomerServiceImpl customerService;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        try {
            // authenticate the customer which is trying to loggedIn
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
            );
        }catch (AuthenticationException authenticationException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // if customer gets the authenticate successfully, then get the User Details
        UserDetails userDetails;
        try {
            userDetails = customerService.loadUserByUsername(loginRequestDto.getEmail());
        }catch (UsernameNotFoundException usernameNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // after getting the user details, get the token
        String token = jwtUtil.generateToken(userDetails.getUsername());  // here username means email

        // Additional logic can be added here if needed

        // return the token
        return new ResponseEntity<>(new LoginResponseDto(token),HttpStatus.OK);

    }
}
