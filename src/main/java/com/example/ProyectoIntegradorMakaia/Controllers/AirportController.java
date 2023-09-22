package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airport;
import com.example.ProyectoIntegradorMakaia.Services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

//     Endpoint para obtener todos los aeropuertos
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

//    Endpoint para obtener un aeropuerto por su id
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);

        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport updateAirport) {
        Airport airport = airportService.updateAirport(id, updateAirport);

        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}