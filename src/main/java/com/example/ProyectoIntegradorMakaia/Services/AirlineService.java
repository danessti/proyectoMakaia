package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Repositories.AirlineRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AirlineService {

    @Autowired
    private final AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline createAirline(Airline airline) {
        return this.airlineRepository.save(airline);
    }

    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    public Airline updateAirline(Long id, Airline updateAirline) {
        Airline existingAirline = airlineRepository.findById(id).orElse(null);

        if (existingAirline != null) {
            existingAirline.setName(updateAirline.getName());
            existingAirline.setFoundedDate(updateAirline.getFoundedDate());
            existingAirline.setWebsite(updateAirline.getWebsite());
            existingAirline.setDescription(updateAirline.getDescription());
            existingAirline.setAirplanes(updateAirline.getAirplanes());
            return airlineRepository.save(existingAirline);
        }

        return null;
    }

    public void deleteAirline(Long id) {
        this.airlineRepository.deleteById(id);
    }

}