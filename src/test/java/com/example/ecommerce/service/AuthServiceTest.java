package com.example.ecommerce.service;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtService jwtService;
    @Mock EmailService emailService;
    @InjectMocks AuthService authService;

    RegisterRequest registerRequest;

    @BeforeEach
    void setup() { registerRequest = new RegisterRequest("Owner", "owner@example.com", "password123"); }

    @Test
    void registerShouldReturnToken() {
        User user = User.builder().name("Owner").email("owner@example.com").password("encoded").role("SHOP_OWNER").build();
        when(userRepository.existsByEmail(registerRequest.email())).thenReturn(false);
        when(userMapper.toEntity(registerRequest)).thenReturn(user);
        when(passwordEncoder.encode(registerRequest.password())).thenReturn("encoded");
        when(jwtService.generateToken("owner@example.com")).thenReturn("token");

        var response = authService.register(registerRequest);

        assertEquals("token", response.accessToken());
        verify(emailService).sendRegistrationConfirmation("owner@example.com", "Owner");
    }

    @Test
    void registerDuplicateShouldThrow() {
        when(userRepository.existsByEmail(registerRequest.email())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> authService.register(registerRequest));
    }

    @Test
    void loginShouldAuthenticate() {
        User user = User.builder().email("owner@example.com").password("encoded").build();
        when(userRepository.findByEmail("owner@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encoded")).thenReturn(true);
        when(jwtService.generateToken("owner@example.com")).thenReturn("token");

        var response = authService.login(new LoginRequest("owner@example.com", "password123"));
        assertEquals("token", response.accessToken());
    }
}
