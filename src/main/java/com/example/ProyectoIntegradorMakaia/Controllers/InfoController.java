package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.InfoContact;
import com.example.ProyectoIntegradorMakaia.Services.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infos")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @PostMapping
    public ResponseEntity<InfoContact> createInfo(@RequestBody InfoContact infoContact) {
        InfoContact newInfoContac = infoService.createInfoContac(infoContact);
        return new ResponseEntity<>(newInfoContac, HttpStatus.CREATED);
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
    public ResponseEntity<InfoContact> updateInfoContac(@PathVariable Long id, @RequestBody InfoContact updateInfoContac) {
        InfoContact infoContac = infoService.updateInfo(id, updateInfoContac);

        if (infoContac != null){
            return new ResponseEntity<>(infoContac, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InfoContact> deleteInfoContac(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}