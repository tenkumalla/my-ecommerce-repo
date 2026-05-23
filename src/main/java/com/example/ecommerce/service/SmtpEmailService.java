package com.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmtpEmailService implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendRegistrationConfirmation(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registration Confirmation");
        message.setText("Hello " + name + ", your shop owner account has been created.");
        mailSender.send(message);
    }
}
