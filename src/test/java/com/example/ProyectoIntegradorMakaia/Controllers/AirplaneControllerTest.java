package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Entities.Airplane;
import com.example.ProyectoIntegradorMakaia.Services.AirplaneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AirplaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirplaneService airplaneService;

    @Test
    void getAllAirplanes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airplanes")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("ad123"))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createAirplane() throws Exception {
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

        Mockito.when(airplaneService.createAirplane(Mockito.any(Airplane.class))).thenReturn(airplane);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/airplanes")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airplane)))
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
    void getAirplaneById() throws Exception {
        long id = 1L;
        Airplane airplane = new Airplane();
        airplane.setId_airplane(id);

        Mockito.when(airplaneService.getAirplaneById(id)).thenReturn(airplane);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airplanes/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateAirplane() throws Exception {
        Long id = 1L;
        Airline airline = new Airline();

        LocalDate localDate = LocalDate.of(1990, 1, 1);
        java.util.Date dateAirline = java.sql.Date.valueOf(localDate);

        airline.setIdAirline(id);
        airline.setName("Avianca");
        airline.setFoundedDate(dateAirline);
        airline.setWebsite("web.com");
        airline.setDescription("Descripción de la aerolínea");

        Long idAirplane = 1L;
        Airplane updateAirplane = new Airplane();

        LocalDate local = LocalDate.parse("1990-01-11");
        Date date = Date.valueOf(local);

        updateAirplane.setId_airplane(id);
        updateAirplane.setAirplaneModel("Model 1");
        updateAirplane.setPassengerCapacity(50);
        updateAirplane.setYearProduction(date);
        updateAirplane.setAirline(airline);

        Mockito.when(airplaneService.updateAirplane(idAirplane, updateAirplane)).thenReturn(updateAirplane);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/airplanes/{id}", idAirplane)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateAirplane)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteAirplane() throws Exception {
        long id = 1L;
        Airplane airplane = new Airplane();
        airplane.setId_airplane(id);

        Mockito.doNothing().when(airplaneService).deleteAirplane(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/airplanes/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}