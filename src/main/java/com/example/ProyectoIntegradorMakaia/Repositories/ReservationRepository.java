package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {}