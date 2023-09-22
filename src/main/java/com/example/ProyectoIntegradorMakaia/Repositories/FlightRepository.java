package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {}