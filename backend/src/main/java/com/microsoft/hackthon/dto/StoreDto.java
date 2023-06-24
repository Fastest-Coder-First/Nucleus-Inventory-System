package com.microsoft.hackthon.dto;

import com.microsoft.hackthon.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto {

    private int id;

    @NotNull(message = "Store name cannot be null")
    private String storeName;

    @NotNull(message = "Store address cannot be null")
    private String storeAddress;
}
