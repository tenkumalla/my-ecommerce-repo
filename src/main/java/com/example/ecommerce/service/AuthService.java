package com.example.ecommerce.service;

import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.UnauthorizedException;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    private final Map<String, User> registrationStore = new ConcurrentHashMap<>();

    public AuthResponse register(RegisterRequest request) {
        if (registrationStore.containsKey(request.email()) || userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Email already registered");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        registrationStore.put(user.getEmail(), user); // mock in-memory response store
        emailService.sendRegistrationConfirmation(user.getEmail(), user.getName());
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, "Bearer", user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        User user = registrationStore.getOrDefault(request.email(), userRepository.findByEmail(request.email()).orElseThrow(() -> new UnauthorizedException("Invalid credentials")));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }
        return new AuthResponse(jwtService.generateToken(user.getEmail()), "Bearer", user.getEmail());
    }
}
