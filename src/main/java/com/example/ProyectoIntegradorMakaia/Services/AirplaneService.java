package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Airplane;
import com.example.ProyectoIntegradorMakaia.Repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository repository;

    @Autowired
    public AirplaneService(AirplaneRepository aircraft) {
        this.repository = aircraft;
    }

    public List<Airplane> getAllAirplanes() {
        return (List<Airplane>) repository.findAll();
    }

    public Airplane createAirplane(Airplane aircraft) {
        return this.repository.save(aircraft);
    }

    public Airplane getAirplaneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Airplane updateAirplane(Long id, Airplane updateAircraft) {
        Airplane existingAirplane = repository.findById(id).orElse(null);

        if (existingAirplane != null) {
            existingAirplane.setAirplaneModel(updateAircraft.getAirplaneModel());
            existingAirplane.setPassengerCapacity(updateAircraft.getPassengerCapacity());
            existingAirplane.setYearProduction(updateAircraft.getYearProduction());
            return repository.save(existingAirplane);
        }

        return null;

    }

    public void deleteAirplane(Long id) {
        this.repository.deleteById(id);
    }

}