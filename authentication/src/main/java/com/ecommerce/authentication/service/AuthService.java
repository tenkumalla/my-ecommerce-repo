package com.ecommerce.authentication.service;

import com.ecommerce.authentication.dto.LoginResponse;
import com.ecommerce.authentication.dto.RegisterRequest;
import com.ecommerce.authentication.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(String email, String password);
}
