package com.ecommerce.authentication.service;

import com.ecommerce.authentication.dto.RegisterRequest;
import com.ecommerce.authentication.entity.ShopOwnerAuth;
import com.ecommerce.authentication.repository.ShopOwnerAuthRepository;
import com.ecommerce.authentication.security.JwtService;
import com.ecommerce.authentication.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock ShopOwnerAuthRepository repository; @Mock PasswordEncoder encoder; @Mock JwtService jwt; @Mock EmailService email;
    @InjectMocks AuthServiceImpl service;

    @Test void registerSuccess() {
        RegisterRequest r = new RegisterRequest(); r.setEmail("a@b.com"); r.setPassword("Password123!"); r.setFirstName("A"); r.setLastName("B"); r.setShopName("S");
        when(repository.findByEmail("a@b.com")).thenReturn(Optional.empty()); when(encoder.encode(anyString())).thenReturn("hash");
        assertEquals("a@b.com", service.register(r).getEmail());
        verify(email).sendRegistrationConfirmation(anyString(), anyString());
    }

    @Test void loginSuccess() {
        ShopOwnerAuth o = ShopOwnerAuth.builder().email("a@b.com").passwordHash("h").build();
        when(repository.findByEmail("a@b.com")).thenReturn(Optional.of(o)); when(encoder.matches("p","h")).thenReturn(true); when(jwt.generateToken("a@b.com")).thenReturn("t");
        assertEquals("t", service.login("a@b.com","p").getAccessToken());
    }
}
