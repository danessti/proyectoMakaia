package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.InfoContact;
import com.example.ProyectoIntegradorMakaia.Services.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/infos")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public ResponseEntity<List<InfoContact>> getAllInfoContact() {
            List<InfoContact> infoContacts = infoService.getAllInfoContacts();
            return new ResponseEntity<>(infoContacts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InfoContact> createInfo(@RequestBody InfoContact infoContact) {
        InfoContact newInfoContact = infoService.createInfoContac(infoContact);
        return new ResponseEntity<>(newInfoContact, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoContact> getInfoById(@PathVariable Long id) {
        InfoContact infoContac = infoService.getInfoById(id);

        if (infoContac != null) {
            return new ResponseEntity<>(infoContac, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoContact> updateInfoContac(@PathVariable Long id, @RequestBody InfoContact updateInfoContact) {
        InfoContact infoContact = infoService.updateInfo(id, updateInfoContact);

        if (infoContact != null){
            return new ResponseEntity<>(infoContact, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InfoContact> deleteInfoContact(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}