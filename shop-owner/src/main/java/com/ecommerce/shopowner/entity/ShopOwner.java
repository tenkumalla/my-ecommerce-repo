package com.ecommerce.shopowner.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="shop_owner") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShopOwner {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String firstName;
 @Column(nullable=false) private String lastName;
 @Column(nullable=false, unique=true) private String email;
 @Column(nullable=false) private String shopName;
}
