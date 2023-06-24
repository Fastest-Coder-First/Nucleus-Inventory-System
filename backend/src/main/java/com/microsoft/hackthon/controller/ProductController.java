package com.microsoft.hackthon.controller;

import com.microsoft.hackthon.dto.ProductDto;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//create rest controller for the product
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;



//    get all products  from product service
    @GetMapping("/products")
    public List<ProductDto> getProducts(){

        return productService.getProducts().stream().map((element) -> modelMapper.map(element, ProductDto.class)).toList();
    }
//
//    //    get a product by id from product service
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable UUID id){
        return productService.getProduct(id);
    }
//
//    //    add product to database from product service
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductDto productDto){
        Product product =  modelMapper.map(productDto, Product.class);
        return productService.addProduct(product);
    }
//
//    //    update product in database from product service
    @PutMapping("/products")
    public Product updateProduct(@RequestBody ProductDto productDto){
        Product product =  modelMapper.map(productDto, Product.class);
        return productService.updateProduct(product);
    }

//       delete product from database from product service
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}
