package com.example.pruebaTecnica.Controller;

import com.example.pruebaTecnica.Configuration.JwtUtil;
import com.example.pruebaTecnica.DTOS.LoginRequest;
import com.example.pruebaTecnica.DTOS.LoginResponse;
import com.example.pruebaTecnica.DTOS.TaskDTO;
import com.example.pruebaTecnica.DTOS.UserDTO;
import com.example.pruebaTecnica.Services.UserSecurityService;
import com.example.pruebaTecnica.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserSecurityService userSecurityService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userSecurityService = userSecurityService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/logins")
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword())
            );
        } catch (Exception e) {
            throw new RuntimeException("Usuario y/o contraseña invalidas.", e);
        }

        final UserDetails userDetails = userSecurityService.loadUserByUsername(authenticationRequest.getUserEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new LoginResponse(jwt);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }
}
