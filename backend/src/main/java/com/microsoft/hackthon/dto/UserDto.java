package com.microsoft.hackthon.dto;

import com.microsoft.hackthon.entity.Address;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

//    create json object for user

    @NotBlank(message = "User name cannot be null and blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "User name should contain only alphabets and spaces")
    @Size(min = 5, max = 200, message = "User name should be between 5 and 200 characters")
    private String userName;

    @NotBlank(message = "User email cannot be null and blank")
    @Email(message = "User email should be valid")
    @Size(min = 5, max = 200, message = "User email should be between 5 and 200 characters")
    private String email;

    @NotNull(message = "Address cannot be null")
    private AddressDto address;

    @NotBlank(message = "User phone cannot be null and blank")
    @Pattern(regexp = "^[0-9]+$", message = "User phone should contain only numbers")
    @Size(min = 10, max = 10, message = "User phone should be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "User password cannot be null and blank")
    @Size(min = 5, max = 20, message = "User password should be between 5 and 200 characters")
    private String password;

}
