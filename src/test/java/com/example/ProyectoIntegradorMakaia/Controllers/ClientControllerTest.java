package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Client;
import com.example.ProyectoIntegradorMakaia.Services.ClientService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void getAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/clients")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("ad123"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createClient() throws Exception {
        Client client = new Client();

        java.sql.Date date = java.sql.Date.valueOf("1999-08-06");

        client.setName("Daniel");
        client.setLastName("Espinosa");
        client.setBirthdate(date);
        client.setGender('M');

        Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/clients")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
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
    void getClientById() throws Exception {
        long id = 1L;
        Client client = new Client();
        client.setId_client(id);

        Mockito.when(clientService.getClientById(id)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/clients/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateClient() throws Exception {
        Long idClient = 1L;
        Client updateClient = new Client();

        java.sql.Date date = java.sql.Date.valueOf("1999-08-06");

        updateClient.setId_client(idClient);
        updateClient.setName("Daniel");
        updateClient.setLastName("Espinosa");
        updateClient.setBirthdate(date);
        updateClient.setGender('M');

        Mockito.when(clientService.updateClient(idClient, updateClient)).thenReturn(updateClient);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/clients/{id}", idClient)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateClient)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClient() throws Exception {
        long id = 1L;
        Client client = new Client();
        client.setId_client(id);

        Mockito.doNothing().when(clientService).deleteClient(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/clients/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}