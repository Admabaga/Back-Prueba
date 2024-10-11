package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.Converters.UserConverter;
import com.example.pruebaTecnica.DTOS.TaskDTO;
import com.example.pruebaTecnica.DTOS.UserDTO;
import com.example.pruebaTecnica.Entitys.User;
import com.example.pruebaTecnica.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserConverter.dtoToEntity(userDTO);
        validations(user);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return UserConverter.entityToDto(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserConverter::entityToDto)
                .sorted(Comparator.comparing(UserDTO::getUserName))
                .collect(Collectors.toList());
    }

    public void validations(User user){
        List<String> userEmails = userRepository.userEmails();
        List<Long> userIds = userRepository.userIds();
        userValidations(user);
        if (userEmails.contains(user.getUserEmail())) {
            throw new RuntimeException(String.format("El correo %s ya ha sido registrado", user.getUserEmail()));
        }
        if (userIds.contains(user.getUserId())) {
            throw new RuntimeException(String.format("La identificacion %s ya ha sido registrada", user.getUserId()));
        }
    }

    public void userValidations(User user){
        if (user.getUserId()== null || String.valueOf(user.getUserId())== "" ) {
            throw new RuntimeException("El campo cedula debe estar diligenciado.");
        }
        if (user.getUserName()== null || user.getUserName()== "" ) {
            throw new RuntimeException("El campo nombre debe estar diligenciado.");
        }
        if (user.getUserEmail()== null || user.getUserEmail()== "" ) {
            throw new RuntimeException("El campo correo debe estar diligenciado.");
        }
        if (user.getUserPassword()== null || user.getUserPassword()== "" ) {
            throw new RuntimeException("El campo contraseña debe estar diligenciado.");
        }
        if (user.getUserRol()== null || user.getUserRol()== "" ) {
            throw new RuntimeException("El campo rol debe estar diligenciado.");
        }
    }
}
