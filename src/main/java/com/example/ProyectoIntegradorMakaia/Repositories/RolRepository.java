package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Roles, Long> {}