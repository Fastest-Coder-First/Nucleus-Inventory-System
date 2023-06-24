package com.microsoft.hackthon.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String userName;

    private String userEmail;

    private String userAddress;

    private String userPhone;

}
