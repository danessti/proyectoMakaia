package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Client;
import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import com.example.ProyectoIntegradorMakaia.Entities.Reservation;
import com.example.ProyectoIntegradorMakaia.Repositories.ClientRepository;
import com.example.ProyectoIntegradorMakaia.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
//    obtener el cliente asociado a la reserva
        Client client = reservation.getClient();

//        obtener el vuelo asociado a la reserva
        Flight flight = reservation.getFlight();

//        verifica si el cliente ya tiene menos de 5 reservas
        if (client != null && client.getReservations().size() >= 5) {
            throw new IllegalArgumentException("El cliente ha alcanzado el límite de reservas.");
        }

//        verifica si el vuelo está completo antes de crear una nueva reserva
        if (isFlightFull(flight)) {
            throw new IllegalArgumentException("El vuelo está completo. No se pueden realizar más reservas.");
        }

//        si no ha alcanzado el límite y no está lleno, guarda la reserva
        return this.reservationRepository.save(reservation);
    }

    private boolean isFlightFull(Flight flight) {
        if (flight == null || flight.getAirplane() == null) {
            return false;
        }

        int capacity = flight.getAirplane().getPassengerCapacity();
        int reservationsCount = flight.getReservations().size();

        return reservationsCount >= capacity;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation updateReservation(Long id, Reservation updateReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);

        if (existingReservation != null) {
            existingReservation.setClient(updateReservation.getClient());
            existingReservation.setFlight(updateReservation.getFlight());
            existingReservation.setDateHourReservation(updateReservation.getDateHourReservation());
            existingReservation.setReservationStatus(updateReservation.getReservationStatus());
            return reservationRepository.save(existingReservation);
        }

        return null;
    }

    public void deleteReservation(Long id) {
        this.reservationRepository.deleteById(id);
    }

}