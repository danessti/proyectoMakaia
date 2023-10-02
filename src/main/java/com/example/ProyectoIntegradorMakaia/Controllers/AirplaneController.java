package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airplane;
import com.example.ProyectoIntegradorMakaia.Services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping
    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        List<Airplane> airplanes = airplaneService.getAllAirplanes();
        return new ResponseEntity<>(airplanes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Airplane> createAirplane(@RequestBody Airplane airplane) {
        Airplane newAirplane = airplaneService.createAirplane(airplane);
        return new ResponseEntity<>(newAirplane, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
        Airplane airplane = airplaneService.getAirplaneById(id);

        if (airplane != null) {
            return new ResponseEntity<>(airplane, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Long id, @RequestBody Airplane updateAirplane) {
        Airplane airplane = airplaneService.updateAirplane(id, updateAirplane);

        if (airplane != null) {
            return new ResponseEntity<>(airplane, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Airplane> deleteAirplane(@PathVariable Long id) {
        airplaneService.deleteAirplane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}