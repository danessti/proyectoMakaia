package com.example.ProyectoIntegradorMakaia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // Genera getters y setters
@AllArgsConstructor // Genera un constructor que acepta todos los campos
@NoArgsConstructor // Genera un constructor sin argumentos
@Entity
@Table(name = "info_contact")
public class InfoContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_info;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private String email;

    @Column(name = "number_phone", nullable = false)
    private String  numberPhone;

    @Column(nullable = false)
    private String address;

}