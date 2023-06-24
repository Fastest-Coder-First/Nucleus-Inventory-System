package com.microsoft.hackthon.service.impl;

import com.microsoft.hackthon.dao.ProductRepository;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.exception.ProductNotFound;
import com.microsoft.hackthon.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    // create crud operation for product class with body


    //    create crud operation on product class

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
//        return product with id from database with product repository

        return productRepository.findById(id).orElseThrow( () -> new ProductNotFound("Product not found") );
    }

    @Override
    public Product addProduct(Product product) {
//        create a method to add product to database with product repository

//        create a add product method
        product.setProductId(UUID.randomUUID());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
//        create a method to update product to database with product repository
        Product existingProduct = productRepository.findById(product.getProductId()).orElseThrow( () -> new ProductNotFound("Product not found") );
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setProductQuantity(product.getProductQuantity());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setRating(product.getRating());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
//        create a method to delete product to database with product repository
        productRepository.findById(id).orElseThrow( () -> new ProductNotFound("Product not found") );
        productRepository.deleteById(id);
    }

}
