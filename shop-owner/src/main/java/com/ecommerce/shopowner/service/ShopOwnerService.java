package com.ecommerce.shopowner.service;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import java.util.List;

public interface ShopOwnerService {
    ShopOwnerDto create(ShopOwnerDto dto);
    ShopOwnerDto getById(Long id);
    List<ShopOwnerDto> getAll();
}
