package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.Entitys.User;
import com.example.pruebaTecnica.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(userEmail)
                .map(this::convertToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el userEmail: " + userEmail));
    }

    private UserDetails convertToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
