package com.ecommerce.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "shop_owner_auth")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ShopOwnerAuth {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String email;
    @Column(nullable = false) private String passwordHash;
    private String firstName;
    private String lastName;
    private String shopName;
}
