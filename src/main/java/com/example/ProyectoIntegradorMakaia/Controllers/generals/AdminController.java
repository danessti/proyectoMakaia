package com.example.ProyectoIntegradorMakaia.Controllers.generals;

import com.example.ProyectoIntegradorMakaia.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}