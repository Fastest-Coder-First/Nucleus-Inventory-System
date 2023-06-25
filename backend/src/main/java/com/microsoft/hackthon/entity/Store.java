package com.microsoft.hackthon.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String storeName;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "store")
    @JsonManagedReference
    private List<Product> productList;

    @OneToOne(cascade = CascadeType.ALL)
    private Address storeAddress;
}
