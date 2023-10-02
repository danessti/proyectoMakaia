package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airport;
import com.example.ProyectoIntegradorMakaia.Services.AirportService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void getAllAirports() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airports")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("ad123"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createAirport() throws Exception {
        Airport airport = new Airport();

        airport.setNameAirport("Aeropuerto");
        airport.setCity("Ciudad");
        airport.setCountry("Pais");

        Mockito.when(airportService.createAirport(Mockito.any(Airport.class))).thenReturn(airport);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/airports")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airport)))
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
    void getAirportById() throws Exception {
        long id = 1L;
        Airport airport = new Airport();
        airport.setId_airport(id);

        Mockito.when(airportService.getAirportById(id)).thenReturn(airport);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/airports/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateAirport() throws Exception {
        Long idAirport = 1L;
        Airport updateAirport = new Airport();

        updateAirport.setId_airport(idAirport);
        updateAirport.setNameAirport("Aeropuerto");
        updateAirport.setCity("Ciudad");
        updateAirport.setCountry("Pais");

        Mockito.when(airportService.updateAirport(idAirport, updateAirport)).thenReturn(updateAirport);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/airports/{id}", idAirport)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateAirport)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteAirport() throws Exception {
        long idAirport = 1L;

        Mockito.doNothing().when(airportService).deleteAirport(idAirport);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/airports/{id}", idAirport)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}