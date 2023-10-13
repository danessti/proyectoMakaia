package com.example.ProyectoIntegradorMakaia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    @JoinColumn(name = "airportOrigin_id")
    private Airport airportOrigin;

    @ManyToOne
    @JoinColumn(name = "airportDestination_id")
    private Airport airportDestination;

//    fecha hora de salida
    @Column(name = "date_hour_exit", nullable = false)
    private Date dateHourExit;

//    fecha hora de llegada
    @Column(name = "date_hour_arrival", nullable = false)
    private Date dateHourArrival;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @Column(name = "code_iata_oaci")
    private String code;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reservation> reservations;

}