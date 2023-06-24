package com.microsoft.hackthon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    //create dto for product class without getter and setter and constructor
    private UUID productId;

    @NotNull(message = "Product name cannot be null")
    private String productName;

    @NotNull(message = "Product quantity cannot be null")
    private String description;

    @NotNull(message = "Product price cannot be null")
    private Double productPrice;

    @NotNull(message = "Product quantity cannot be null")
    private Double productQuantity;

    @NotNull(message = "Product category cannot be null")
    private String category;

    @NotNull(message = "Store id cannot be null")
    private Integer storeId;

    @NotNull(message = "Product rating cannot be null")
    @Size(min = 0, max = 5, message = "Rating should be between 0 and 5")
    private Double rating;

}

// create a json for product dto



