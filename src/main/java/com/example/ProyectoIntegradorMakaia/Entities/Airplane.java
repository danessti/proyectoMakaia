package com.example.ProyectoIntegradorMakaia.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_airplane;

//    segun la mi indagado la traducción más adecuada para ''modelo de avion' es "aircraft model"
    @Column(name = "airplane_model",nullable = false)
    private String airplaneModel;

    @Column(name = "passenger_capacity",nullable = false)
    private int passengerCapacity;

//    Traducción más usada para año de fabricación al inglés
    @Column(name = "year_production",nullable = false)
    private Date yearProduction;

}