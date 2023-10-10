package com.example.ProyectoIntegradorMakaia.Controllers.generals;

import com.example.ProyectoIntegradorMakaia.Entities.Airport;
import com.example.ProyectoIntegradorMakaia.Entities.Flight;
import com.example.ProyectoIntegradorMakaia.Entities.Reservation;
import com.example.ProyectoIntegradorMakaia.Services.AirportService;
import com.example.ProyectoIntegradorMakaia.Services.FlightService;
import com.example.ProyectoIntegradorMakaia.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/v1/public")
@RestController
public class PublicController {

    @Autowired
    private final FlightService flightService;

    @Autowired
    private final AirportService airportService;

    @Autowired
    private final ReservationService reservationService;

    // Métodos GET
    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Métodos POST
    @PostMapping("/flights")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PostMapping("/airports")
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // Métodos DELETE
    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @DeleteMapping("/airports/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

}