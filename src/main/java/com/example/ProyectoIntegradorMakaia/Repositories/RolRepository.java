package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Models.Authority;
import com.example.ProyectoIntegradorMakaia.Models.Roles;
import com.example.ProyectoIntegradorMakaia.Utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Roles, Long> {
    Optional<Authority> findByName(AuthorityName name);
}
