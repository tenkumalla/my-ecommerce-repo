package com.ecommerce.authentication.service.impl;

import com.ecommerce.authentication.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j @Service @Profile({"default","dev","local"})
public class MockEmailService implements EmailService {
    public void sendRegistrationConfirmation(String email, String fullName) { log.info("Mock email sent to {} for {}", email, fullName); }
}
