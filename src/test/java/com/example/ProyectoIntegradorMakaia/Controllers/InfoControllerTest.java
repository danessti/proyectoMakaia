package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Client;
import com.example.ProyectoIntegradorMakaia.Entities.InfoContact;
import com.example.ProyectoIntegradorMakaia.Services.InfoService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Test
    void createInfo() throws Exception {
        Client client = new Client();

        java.sql.Date date = java.sql.Date.valueOf("1999-08-06");

        client.setName("Daniel");
        client.setLastName("Espinosa");
        client.setBirthdate(date);
        client.setGender('M');

        InfoContact infoContact = new InfoContact();

        infoContact.setClient(client);
        infoContact.setEmail("correo@gmail.com");
        infoContact.setNumberPhone("123456");
        infoContact.setAddress("123 Calle Principal");

        Mockito.when(infoService.createInfoContac(Mockito.any(InfoContact.class))).thenReturn(infoContact);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/infos")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(infoContact)))
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
    void getInfoById() throws Exception {
        Long id = 1L;
        InfoContact infoContact = new InfoContact();
        infoContact.setId_info(id);

        Mockito.when(infoService.getInfoById(id)).thenReturn(infoContact);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/infos/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateInfoContact() throws Exception {
        Client client = new Client();

        java.sql.Date date = java.sql.Date.valueOf("1999-08-06");

        client.setName("Daniel");
        client.setLastName("Espinosa");
        client.setBirthdate(date);
        client.setGender('M');

        Long idInfo = 1L;
        InfoContact updateInfo = new InfoContact();

        updateInfo.setId_info(idInfo);
        updateInfo.setClient(client);
        updateInfo.setEmail("correo@gmail.com");
        updateInfo.setNumberPhone("123456");
        updateInfo.setAddress("123 Calle Principal");

        Mockito.when(infoService.updateInfo(idInfo, updateInfo)).thenReturn(updateInfo);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/infos/{id}", idInfo)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateInfo)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteInfoContact() throws Exception {
        Long id = 1L;
        InfoContact infoContact = new InfoContact();
        infoContact.setId_info(id);

        Mockito.doNothing().when(infoService).deleteInfo(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/infos/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "ad123")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}