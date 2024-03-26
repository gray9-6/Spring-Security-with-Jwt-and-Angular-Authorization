package com.example.springsecuritywithangular.services.jwt;

import com.example.springsecuritywithangular.entities.Customer;
import com.example.springsecuritywithangular.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // write logic to fetch customer from db
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with this email" + email));

        return new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());
    }
}
