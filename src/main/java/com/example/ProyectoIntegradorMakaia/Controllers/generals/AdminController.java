package com.example.ProyectoIntegradorMakaia.Controllers.generals;

import com.example.ProyectoIntegradorMakaia.Entities.*;
import com.example.ProyectoIntegradorMakaia.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/v1/admin")
@RestController
public class AdminController {

    @Autowired
    private final AirlineService airlineService;

    @Autowired
    private final AirplaneService airplaneService;

    @Autowired
    private final AirportService airportService;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final FlightService flightService;

    @Autowired
    private final InfoService infoService;

    @Autowired
    private final ReservationService reservationService;

    // Métodos GET
    @GetMapping("/airlines")
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/airplanes")
    public List<Airplane> getAllAirplanes() {
        return airplaneService.getAllAirplanes();
    }

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/infos")
    public List<InfoContact> getAllInfos() {
        return infoService.getAllInfoContacts();
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Métodos POST
    @PostMapping("/airlines")
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    @PostMapping("/airplanes")
    public Airplane createAirplane(@RequestBody Airplane airplane) {
        return airplaneService.createAirplane(airplane);
    }

    @PostMapping("/airports")
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PostMapping("/flights")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PostMapping("/infos")
    public InfoContact createInfo(@RequestBody InfoContact info) {
        return infoService.createInfoContac(info);
    }

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @DeleteMapping("/airlines/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }

    @DeleteMapping("/airplanes/{id}")
    public void deleteAirplane(@PathVariable Long id) {
        airplaneService.deleteAirplane(id);
    }

    @DeleteMapping("/airports/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @DeleteMapping("/infos/{id}")
    public void deleteInfo(@PathVariable Long id) {
        infoService.deleteInfo(id);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

}