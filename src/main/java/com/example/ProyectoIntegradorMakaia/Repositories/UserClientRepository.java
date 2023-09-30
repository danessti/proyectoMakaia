package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClientRepository extends JpaRepository<UserClient, Long> {
    // Metodo para retornar un usuario por su nombre
    Optional<UserClient> findByUsernameOrEmail(String username, String email );
}