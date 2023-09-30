package com.example.ProyectoIntegradorMakaia.Models.user;

import com.example.ProyectoIntegradorMakaia.Models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Metodo para retornar un usuario por su nombre
    Optional<User> findByUsernameOrEmail(String username, String email );
}