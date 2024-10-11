package com.example.pruebaTecnica.Converters;

import com.example.pruebaTecnica.DTOS.UserDTO;
import com.example.pruebaTecnica.Entitys.User;

public class UserConverter {

    public static User dtoToEntity(UserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserRol(userDTO.getUserRol());
        user.setUserPassword(userDTO.getUserPassword());
        return user;
    }

    public static UserDTO entityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserRol(user.getUserRol());
        userDTO.setUserPassword(user.getUserPassword());
        return userDTO;
    }
}
