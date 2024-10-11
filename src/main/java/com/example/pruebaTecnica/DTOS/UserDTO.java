package com.example.pruebaTecnica.DTOS;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userRol;
}
