package com.microsoft.hackthon.dto;

import com.microsoft.hackthon.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto {

    private int id;

    @NotBlank(message = "Store name cannot be null and blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Store name should contain only alphabets and spaces")
    @Size(min = 5, max = 200, message = "Store name should be between 5 and 100 characters")
    private String storeName;

    @NotNull(message = "Store address cannot be null ")
    private AddressDto storeAddress;
}
