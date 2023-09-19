package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Airport;
import com.example.ProyectoIntegradorMakaia.Repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return (List<Airport>) airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport createAirport(Airport airport) {
        return this.airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport updateAirport) {
        Airport existingAirport = airportRepository.findById(id).orElse(null);

        if (existingAirport != null) {
            existingAirport.setNameAirport(updateAirport.getNameAirport());
            existingAirport.setCode(updateAirport.getCode());
            existingAirport.setCity(updateAirport.getCity());
            existingAirport.setCountry(updateAirport.getCountry());
            return airportRepository.save(existingAirport);
        }

        return null;
    }

    public void deleteAirport(Long id) {
        this.airportRepository.deleteById(id);
    }

}