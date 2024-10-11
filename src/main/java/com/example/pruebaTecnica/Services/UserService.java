package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.DTOS.UserDTO;
import com.example.pruebaTecnica.Entitys.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getUsers();
}
