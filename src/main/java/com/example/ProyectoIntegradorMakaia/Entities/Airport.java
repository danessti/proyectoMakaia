package com.example.ProyectoIntegradorMakaia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_airport;

    @Column(name = "name_airport",nullable = false)
    private String nameAirport;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "airportOrigin", cascade = CascadeType.ALL)
    private Set<Flight> flightsOrigin;

    @JsonIgnore
    @OneToMany(mappedBy = "airportDestination")
    private List<Flight> flightsDestination;

}