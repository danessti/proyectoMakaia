package com.example.ProyectoIntegradorMakaia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airline")
public class Airline {

    @Id
    @Column(name = "id_airline")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAirline;

    @Column(nullable = false)
    private String name;

//    el campo unique = true es para no se repita otro codigo de vuelo ya que cada uno debe ser único
//    @Column(name = "code_iata", unique = true)
//    private String code;

    @Column(name = "founded_date", nullable = false)
    private Date foundedDate;

    @Column(name = "website")
    private String website;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "airline")
    @JsonIgnore
    private List<Airplane> airplanes;

}