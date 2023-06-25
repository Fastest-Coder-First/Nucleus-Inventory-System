package com.microsoft.hackthon.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    //create dto for product class without getter and setter and constructor
    //create a json for product dto
    private UUID productId;

    @NotBlank(message = "Product name cannot be blank and null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Product name should contain only alphabets and no special characters or numbers")
    private String productName;

    @NotBlank(message = "Product quantity cannot be null and blank")
    @Size(min = 0, max = 200, message = "Product description should be between 0 and 100 characters")
    private String description;

    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Product price should be greater than 0")
    @Size(min = 0, max = 100000000, message = "Product price should be between 0 and 100000 inclusive of both the numbers")
    private Double productPrice;

    @NotNull(message = "Product quantity cannot be null and blank")
    @Min(value = 0, message = "Product quantity should be greater than 0")
    @Size(min = 0, max = 100000000, message = "Product quantity should be between 0 and 100000 inclusive of both the numbers")
    private Double productQuantity;

    @NotBlank(message = "Product category cannot be null and blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Product category should contain only alphabets and no special characters or numbers")
    private String category;

    @NotNull(message = "Store id cannot be null")
    private Integer storeId;

    @NotNull(message = "Product rating cannot be null ")
    @Size(min = 0, max = 5, message = "Rating should be between 0 and 5 inclusive of both the numbers")
//    @DecimalMin(value = "0.0", message = "Rating should be between 0 and 5 inclusive of both the numbers")
//    @DecimalMax(value = "5.0", message = "Rating should be between 0 and 5 inclusive of both the numbers")
    private Double rating;

}

// create a json for product dto



