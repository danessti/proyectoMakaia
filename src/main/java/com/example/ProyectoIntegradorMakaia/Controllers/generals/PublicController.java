package com.example.ProyectoIntegradorMakaia.Controllers.generals;

import com.example.ProyectoIntegradorMakaia.Services.AirportService;
import com.example.ProyectoIntegradorMakaia.Services.FlightService;
import com.example.ProyectoIntegradorMakaia.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}