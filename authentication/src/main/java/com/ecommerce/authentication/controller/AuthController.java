package com.ecommerce.authentication.controller;

import com.ecommerce.authentication.dto.LoginResponse;
import com.ecommerce.authentication.dto.RegisterRequest;
import com.ecommerce.authentication.dto.RegisterResponse;
import com.ecommerce.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){ return ResponseEntity.ok(authService.register(request)); }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestHeader("Authorization") String authorization){
        String decoded = new String(Base64.getDecoder().decode(authorization.replace("Basic ", "")), StandardCharsets.UTF_8);
        String[] parts = decoded.split(":", 2);
        return ResponseEntity.ok(authService.login(parts[0], parts[1]));
    }
}
