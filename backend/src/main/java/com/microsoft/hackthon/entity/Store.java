package com.microsoft.hackthon.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String storeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private List<Product> productList;

    private String storeAddress;
}
