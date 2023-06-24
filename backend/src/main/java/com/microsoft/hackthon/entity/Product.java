package com.microsoft.hackthon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String productName;

    private Double productQuantity;

    private Double productPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private String description;

    private String category;

    private Double rating;
}
