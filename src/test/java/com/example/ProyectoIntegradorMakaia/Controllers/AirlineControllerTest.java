package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Services.AirlineService;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AirlineControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Test
    void getAllAirlines() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airlines")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("ad123"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createAirline() throws Exception {

        Airline airline = new Airline();

        Mockito.when(airlineService.createAirline(Mockito.any(Airline.class))).thenReturn(airline);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/airlines")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airline))) // Convierte el objeto Airline a JSON
                .andExpect(MockMvcResultMatchers.status().isCreated()); // verifica que se devuelva un código de estado '201 CREATED'

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAirlineById() throws Exception {

        long idAirline = 1L;
        Airline airline = new Airline();
        airline.setIdAirline(idAirline);

        Mockito.when(airlineService.getAirlineById(idAirline)).thenReturn(airline);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airlines/{id}", idAirline)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateAirline() {
    }

    @Test
    void deleteAirline() {
    }

}