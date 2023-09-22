package com.example.ProyectoIntegradorMakaia.Services;

import com.example.ProyectoIntegradorMakaia.Entities.InfoContact;
import com.example.ProyectoIntegradorMakaia.Repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private final InfoRepository infoRepository;

    @Autowired
    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public InfoContact createInfoContac(InfoContact infoContact) {
        return this.infoRepository.save(infoContact);
    }

    public List<InfoContact> getAllInfoContacts() {
        return (List<InfoContact>) infoRepository.findAll();
    }

    public InfoContact getInfoById(Long id) {
        return infoRepository.findById(id).orElse(null);
    }

    public InfoContact updateInfo(Long id, InfoContact updateInfo) {
        InfoContact existingInfo = infoRepository.findById(id).orElse(null);

        if (existingInfo != null) {
            existingInfo.setClient(updateInfo.getClient());
            existingInfo.setEmail(updateInfo.getEmail());
            existingInfo.setNumberPhone(updateInfo.getNumberPhone());
            existingInfo.setAddress(updateInfo.getAddress());
            return infoRepository.save(existingInfo);
        }

        return null;
    }

    public void deleteInfo(Long id) {
        this.infoRepository.deleteById(id);
    }

}