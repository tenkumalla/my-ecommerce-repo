package com.ecommerce.shopowner.controller;

import com.ecommerce.shopowner.dto.ShopOwnerDto;
import com.ecommerce.shopowner.service.ShopOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/shop-owners") @RequiredArgsConstructor
public class ShopOwnerController {
    private final ShopOwnerService service;
    @PostMapping public ResponseEntity<ShopOwnerDto> create(@Valid @RequestBody ShopOwnerDto dto){ return ResponseEntity.ok(service.create(dto)); }
    @GetMapping("/{id}") public ResponseEntity<ShopOwnerDto> getById(@PathVariable Long id){ return ResponseEntity.ok(service.getById(id)); }
    @GetMapping public ResponseEntity<List<ShopOwnerDto>> getAll(){ return ResponseEntity.ok(service.getAll()); }
}
