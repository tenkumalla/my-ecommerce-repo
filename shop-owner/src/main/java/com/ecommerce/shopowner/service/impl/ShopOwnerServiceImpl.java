package com.ecommerce.shopowner.service.impl;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import com.ecommerce.shopowner.exception.ResourceNotFoundException;
import com.ecommerce.shopowner.mapper.ShopOwnerMapper;
import com.ecommerce.shopowner.repository.ShopOwnerRepository;
import com.ecommerce.shopowner.service.ShopOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ShopOwnerServiceImpl implements ShopOwnerService {
    private final ShopOwnerRepository repository; private final ShopOwnerMapper mapper;
    public ShopOwnerDto create(ShopOwnerDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    public ShopOwnerDto getById(Long id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shop owner not found"))); }
    public List<ShopOwnerDto> getAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
}
