package com.example.pruebaTecnica.Repositories;

import com.example.pruebaTecnica.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.userEmail FROM User u")
    List<String> userEmails();

    @Query("SELECT u.userId FROM User u")
    List<Long> userIds();

    @Query("SELECT u FROM User u WHERE u.userEmail = :correo")
    Optional<User> getUserByEmail(@Param("correo") String userEmail);
}
