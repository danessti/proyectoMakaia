package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.Client;
import com.example.ProyectoIntegradorMakaia.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return this.clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updateClient) {
        Client existingClient = clientRepository.findById(id).orElse(null);

        if (existingClient != null) {
            existingClient.setName(updateClient.getName());
            existingClient.setLastName(updateClient.getLastName());
            existingClient.setBirthdate(updateClient.getBirthdate());
            existingClient.setGender(updateClient.getGender());
            return clientRepository.save(existingClient);
        }

        return null;
    }

    public void deleteClient(Long id) {
        this.clientRepository.deleteById(id);
    }

}