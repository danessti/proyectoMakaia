package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.*;
import com.example.ProyectoIntegradorMakaia.Services.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    void getAllFlights() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("ad123"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createFlight() throws Exception {
        Airline airline = new Airline();

        LocalDate localDate = LocalDate.of(1990, 1, 1);
        java.util.Date dateAirline = java.sql.Date.valueOf(localDate);

        airline.setIdAirline(1L);
        airline.setName("Avianca");
        airline.setFoundedDate(dateAirline);
        airline.setWebsite("web.com");
        airline.setDescription("Descripción de la aerolínea");

        Airplane airplane = new Airplane();

        LocalDate local = LocalDate.parse("1990-01-11");
        Date date = Date.valueOf(local);

        airplane.setId_airplane(1L);
        airplane.setAirplaneModel("Model 1");
        airplane.setPassengerCapacity(50);
        airplane.setYearProduction(date);
        airplane.setAirline(airline);

        Long id = 1L;
        Airport airport = new Airport();

        airport.setId_airport(id);
        airport.setNameAirport("Aeropuerto");
        airport.setCity("Ciudad");
        airport.setCountry("Pais");

        Long id2 = 1L;
        Airport airport2 = new Airport();

        airport2.setId_airport(id2);
        airport2.setNameAirport("Aeropuerto 2");
        airport2.setCity("Ciudad 2");
        airport2.setCountry("Pais 2");

        LocalDate localExit = LocalDate.parse("2023-09-25");
        Date dateExit = Date.valueOf(localExit);

        LocalDate localArrival = LocalDate.parse("2023-09-24");
        Date dateArrival = Date.valueOf(localArrival);

        Flight flight = new Flight();

        flight.setAirportOrigin(airport);
        flight.setAirportDestination(airport2);
        flight.setDateHourExit(dateExit);
        flight.setDateHourArrival(dateArrival);
        flight.setAirplane(airplane);

        Mockito.when(flightService.createFlight(Mockito.any(Flight.class))).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/flights")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(flight)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFlightById() throws Exception {
        Long id = 1L;
        Flight flight = new Flight();
        flight.setId_flight(id);

        Mockito.when(flightService.getFlightById(id)).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateFlight() throws Exception {
        Airline airline = new Airline();

        LocalDate localDate = LocalDate.of(1990, 1, 1);
        java.util.Date dateAirline = java.sql.Date.valueOf(localDate);

        airline.setIdAirline(1L);
        airline.setName("Avianca");
        airline.setFoundedDate(dateAirline);
        airline.setWebsite("web.com");
        airline.setDescription("Descripción de la aerolínea");

        Airplane airplane = new Airplane();

        LocalDate local = LocalDate.parse("1990-01-11");
        Date date = Date.valueOf(local);

        airplane.setId_airplane(1L);
        airplane.setAirplaneModel("Model 1");
        airplane.setPassengerCapacity(50);
        airplane.setYearProduction(date);
        airplane.setAirline(airline);

        Long id = 1L;
        Airport airport = new Airport();

        airport.setId_airport(id);
        airport.setNameAirport("Aeropuerto");
        airport.setCity("Ciudad");
        airport.setCountry("Pais");

        Long id2 = 1L;
        Airport airport2 = new Airport();

        airport2.setId_airport(id2);
        airport2.setNameAirport("Aeropuerto 2");
        airport2.setCity("Ciudad 2");
        airport2.setCountry("Pais 2");

        LocalDate localExit = LocalDate.parse("2023-09-25");
        Date dateExit = Date.valueOf(localExit);

        LocalDate localArrival = LocalDate.parse("2023-09-24");
        Date dateArrival = Date.valueOf(localArrival);

        Long idFlight = 1L;
        Flight updateFlight = new Flight();

        updateFlight.setId_flight(idFlight);
        updateFlight.setAirportOrigin(airport);
        updateFlight.setAirportDestination(airport2);
        updateFlight.setDateHourExit(dateExit);
        updateFlight.setDateHourArrival(dateArrival);
        updateFlight.setAirplane(airplane);

        Mockito.when(flightService.updateFlight(idFlight, updateFlight)).thenReturn(updateFlight);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/flights/{id}", idFlight)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateFlight)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFlight() throws Exception {
        Long id = 1L;
        Flight flight = new Flight();
        flight.setId_flight(id);

        Mockito.doNothing().when(flightService).deleteFlight(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/flights/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}