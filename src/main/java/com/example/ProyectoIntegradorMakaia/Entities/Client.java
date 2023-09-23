package com.example.ProyectoIntegradorMakaia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor // Crea un constructor  argumentos
@NoArgsConstructor // Crea un constructor sin argumentos
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthdate;

//    la traducción más usada para referirse al género en inglés
    @Column(nullable = false)
    private char gender;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Reservation> reservations;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private InfoContact infoContact;

}