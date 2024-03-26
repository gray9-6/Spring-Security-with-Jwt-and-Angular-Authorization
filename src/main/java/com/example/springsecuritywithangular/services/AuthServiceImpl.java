package com.example.springsecuritywithangular.services;

import com.example.springsecuritywithangular.dto.SignupRequestDto;
import com.example.springsecuritywithangular.entities.Customer;
import com.example.springsecuritywithangular.repositories.CustomerRepository;
import com.example.springsecuritywithangular.transformer.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean createCustomer(SignupRequestDto signupRequestDto) {
        // check if customer already exists
        if(customerRepository.findByEmail(signupRequestDto.getEmail()).isPresent()){
            return false;
        }

        // if user is not present then create the customer
        // convert the dto to Customer
        Customer customer = CustomerTransformer.SignupRequestDtoToCustomer(signupRequestDto);
        customer.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

        // save the customer
        customerRepository.save(customer);

        return true;
    }
}
