package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Models.Authority;
import com.example.ProyectoIntegradorMakaia.Utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    // Metodo para retornar el rol por el nombre
    Optional<Authority> findByName(AuthorityName name);
}