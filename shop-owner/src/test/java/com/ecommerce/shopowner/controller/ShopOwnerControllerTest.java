package com.ecommerce.shopowner.controller;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import com.ecommerce.shopowner.service.ShopOwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShopOwnerControllerTest {
    @Test void getByIdOk() throws Exception {
        ShopOwnerService service = Mockito.mock(ShopOwnerService.class);
        Mockito.when(service.getById(1L)).thenReturn(new ShopOwnerDto());
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new ShopOwnerController(service)).build();
        mvc.perform(get("/api/shop-owners/1")).andExpect(status().isOk());
    }
}
