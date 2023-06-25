package com.microsoft.hackthon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Store addressLine should contain only alphabets, numbers and spaces")
    @Size(min = 5, max = 200, message = "Store address should be between 5 and 100 characters")
    private String addressLine;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Store city should contain only alphabets, numbers and spaces")
    @Size(min = 5, max = 200, message = "Store city should be between 5 and 100 characters")
    private String city;
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Store state should contain only alphabets, numbers and spaces")
    @Size(min = 5, max = 200, message = "Store state should be between 5 and 100 characters")
    private String state;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Store country should contain only alphabets, numbers and spaces")
    @Size(min = 5, max = 200, message = "Store country should be between 5 and 100 characters")
    private String country;

}
