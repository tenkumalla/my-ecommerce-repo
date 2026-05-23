package com.ecommerce.shopowner.mapper;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import com.ecommerce.shopowner.entity.ShopOwner;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerMapper {
    public ShopOwnerDto toDto(ShopOwner e){ ShopOwnerDto d = new ShopOwnerDto(); d.setId(e.getId()); d.setFirstName(e.getFirstName()); d.setLastName(e.getLastName()); d.setEmail(e.getEmail()); d.setShopName(e.getShopName()); return d; }
    public ShopOwner toEntity(ShopOwnerDto d){ return ShopOwner.builder().id(d.getId()).firstName(d.getFirstName()).lastName(d.getLastName()).email(d.getEmail()).shopName(d.getShopName()).build(); }
}
