package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "SHOP_OWNER")
    User toEntity(RegisterRequest request);
}
