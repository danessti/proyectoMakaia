package com.example.ProyectoIntegradorMakaia.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_flight;
 
    @ManyToOne
    @JoinColumn(name = "airportOrigin_id", nullable = false)
    private Airport airportOrigin;

    @ManyToOne
    @JoinColumn(name = "airportDestination_id", nullable = false)
    private Airport airportDestination;

    @Column(nullable = false)
    private Date fecha_hora_salida;

    @Column(nullable = false)
    private Date fecha_hora_llegada;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @ManyToMany(mappedBy = "flights")
    private Set<Reservation> reservations = new HashSet<>();

}