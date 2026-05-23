package com.example.ecommerce.service;

public interface EmailService {
    void sendRegistrationConfirmation(String email, String name);
}
