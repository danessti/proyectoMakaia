package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import com.example.ProyectoIntegradorMakaia.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
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
            existingFlight.setReservations(updateFlight.getReservations());
            return flightRepository.save(existingFlight);
        }

        return null;
    }

    public void deleteFlight(Long id) {
        this.flightRepository.deleteById(id);
    }

}