package com.microsoft.hackthon.service;


import com.microsoft.hackthon.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

//    create crud operation on product class
    public List<Product> getProducts();
    public Product getProduct(UUID id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(UUID id);


}
