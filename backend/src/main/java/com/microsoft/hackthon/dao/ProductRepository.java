package com.microsoft.hackthon.dao;

import com.microsoft.hackthon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    //    find product quantity
    Product findByProductQuantity(Double productQuantity);

}
