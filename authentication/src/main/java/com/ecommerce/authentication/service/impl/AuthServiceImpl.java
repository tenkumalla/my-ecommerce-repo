package com.ecommerce.authentication.service.impl;

import com.ecommerce.authentication.dto.*;
import com.ecommerce.authentication.entity.ShopOwnerAuth;
import com.ecommerce.authentication.exception.ApiException;
import com.ecommerce.authentication.repository.ShopOwnerAuthRepository;
import com.ecommerce.authentication.security.JwtService;
import com.ecommerce.authentication.service.AuthService;
import com.ecommerce.authentication.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ShopOwnerAuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final Map<String, RegisterResponse> registerCache = new ConcurrentHashMap<>();

    public RegisterResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) throw new ApiException("Email already exists");
        repository.save(ShopOwnerAuth.builder().email(request.getEmail()).passwordHash(passwordEncoder.encode(request.getPassword())).firstName(request.getFirstName()).lastName(request.getLastName()).shopName(request.getShopName()).build());
        RegisterResponse response = new RegisterResponse("Registration successful. Confirmation email sent.", request.getEmail());
        registerCache.put(request.getEmail(), response);
        emailService.sendRegistrationConfirmation(request.getEmail(), request.getFirstName()+" "+request.getLastName());
        return response;
    }

    public LoginResponse login(String email, String password) {
        ShopOwnerAuth owner = repository.findByEmail(email).orElseThrow(() -> new ApiException("Invalid credentials"));
        if (!passwordEncoder.matches(password, owner.getPasswordHash())) throw new ApiException("Invalid credentials");
        return new LoginResponse(jwtService.generateToken(email), "Bearer");
    }
}
