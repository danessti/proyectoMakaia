package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Reservation;
import com.example.ProyectoIntegradorMakaia.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getAllReservations() {
        return (List<Reservation>) repository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        return this.repository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Reservation updateReservation(Long id, Reservation updateReservation) {
        Reservation existingReservation = repository.findById(id).orElse(null);

        if (existingReservation != null) {
            existingReservation.setClient(updateReservation.getClient());
            existingReservation.setFlights(updateReservation.getFlights());
            existingReservation.setDateHourReservation(updateReservation.getDateHourReservation());
            existingReservation.setReservationStatus(updateReservation.getReservationStatus());
            return repository.save(existingReservation);
        }

        return null;
    }

    public void deleteReservation(Long id) {
        this.repository.deleteById(id);
    }

}