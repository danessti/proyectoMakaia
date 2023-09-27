package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Entities.Airplane;
import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import com.example.ProyectoIntegradorMakaia.Repositories.AirlineRepository;
import com.example.ProyectoIntegradorMakaia.Repositories.AirplaneRepository;
import com.example.ProyectoIntegradorMakaia.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private final FlightRepository flightRepository;

    @Autowired
    private final AirlineRepository airlineRepository;

    private final AirplaneRepository airplaneRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirlineRepository airlineRepository, AirplaneRepository airplaneRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
        this.airplaneRepository = airplaneRepository;
    }

    public List<Flight> getAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {

//        Verifica la proporción de los datos requeridos
        if (flight.getAirplane() == null || flight.getAirportOrigin() == null || flight.getAirportDestination() == null) {
            throw new IllegalArgumentException("Se requiere una aerolínea con código para crear un vuelo");
        }

//        obtener el avión a partir del idAirplane
        Long idAirplane = flight.getAirplane().getId_airplane();
        Airplane airplane = airplaneRepository.findById(idAirplane)
                .orElseThrow(() -> new IllegalArgumentException("Avión con id " + idAirplane + " no encontrado"));

//        obtener la aerolínea asociada al avión
        Airline airline = airplane.getAirline();

//        verifica que la aerolínea tenga al menos 2 caracteres en su nombre
        if (airline.getName().length() < 2) {
            throw new IllegalArgumentException("El nombre de la aerolínea debe tener al menos 2 caracteres.");
        }

//        consultar la cantidad actual de vuelo asociado a la aerolínea
        int flightCount = flightRepository.countByAirline(airline);

//        formatear el número obtenido en un formato de cuatro dígitos
        String codeConsetive = String.format("%04d", flightCount + 1);

//        generar el código consecutivo para el vuelo
        String codeAirline = airline.getName().substring(0, 2).toUpperCase();

//        generar el código IATA final
        String codeIata = codeAirline + " " + codeConsetive;

//        se guarda el codigo del vuelo creado anteriormente
        flight.setCode(codeIata);

//       se guarda el vuelo en la base de datos
        return this.flightRepository.save(flight);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight updateFlight(Long id, Flight updateFlight) {
        Flight existingFlight = flightRepository.findById(id).orElse(null);

        if (existingFlight != null) {
            existingFlight.setAirportOrigin(updateFlight.getAirportOrigin());
            existingFlight.setAirportDestination(updateFlight.getAirportDestination());
            existingFlight.setDateHourExit(updateFlight.getDateHourExit());
            existingFlight.setAirportDestination(updateFlight.getAirportDestination());
            existingFlight.setAirplane(updateFlight.getAirplane());
            existingFlight.setReservation(updateFlight.getReservation());
            return flightRepository.save(existingFlight);
        }

        return null;
    }

    public void deleteFlight(Long id) {
        this.flightRepository.deleteById(id);
    }

}