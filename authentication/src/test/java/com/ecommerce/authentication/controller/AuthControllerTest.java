package com.ecommerce.authentication.controller;

import com.ecommerce.authentication.dto.LoginResponse;
import com.ecommerce.authentication.dto.RegisterResponse;
import com.ecommerce.authentication.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {
    @Test void loginOk() throws Exception {
        AuthService service = Mockito.mock(AuthService.class);
        Mockito.when(service.login("owner@example.com", "Password123!")).thenReturn(new LoginResponse("token","Bearer"));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new AuthController(service)).build();
        mvc.perform(post("/api/auth/login").header("Authorization", "Basic b3duZXJAZXhhbXBsZS5jb206UGFzc3dvcmQxMjMh").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
