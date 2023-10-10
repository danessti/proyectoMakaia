package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Models.Authority;
import com.example.ProyectoIntegradorMakaia.Utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    // Método para retornar la autoridad por el nombre
    Optional<Authority> findByName(AuthorityName name);
}