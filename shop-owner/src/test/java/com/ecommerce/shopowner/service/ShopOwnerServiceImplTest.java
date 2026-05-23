package com.ecommerce.shopowner.service;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import com.ecommerce.shopowner.entity.ShopOwner;
import com.ecommerce.shopowner.mapper.ShopOwnerMapper;
import com.ecommerce.shopowner.repository.ShopOwnerRepository;
import com.ecommerce.shopowner.service.impl.ShopOwnerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopOwnerServiceImplTest {
    @Mock ShopOwnerRepository repository; @Spy ShopOwnerMapper mapper; @InjectMocks ShopOwnerServiceImpl service;
    @Test void createOk(){
        ShopOwnerDto dto = new ShopOwnerDto(); dto.setFirstName("A"); dto.setLastName("B"); dto.setEmail("a@b.com"); dto.setShopName("S");
        when(repository.save(Mockito.any())).thenAnswer(i -> { ShopOwner s=i.getArgument(0); s.setId(1L); return s;});
        assertEquals(1L, service.create(dto).getId());
    }
}
