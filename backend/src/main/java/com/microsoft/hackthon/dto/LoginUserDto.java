package com.microsoft.hackthon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDto {

    @NotBlank(message = "User email cannot be null and blank")
    @Email(message = "User email should be valid")
    @Size(min = 5, max = 200, message = "User email should be between 5 and 200 characters")
    private String email;

    @NotBlank(message = "User password cannot be null and blank")
    @Size(min = 5, max = 20, message = "User password should be between 5 and 200 characters")
    private String password;

}
