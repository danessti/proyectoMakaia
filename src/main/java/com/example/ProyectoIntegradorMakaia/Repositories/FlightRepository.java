package com.example.ProyectoIntegradorMakaia.Repositories;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

//    método personalizado para contar vuelos por aerolínea
    @Query("SELECT COUNT(f) FROM Flight f WHERE f.airplane.airline = :airline")
    int countByAirline(@Param("airline") Airline airlines);

}