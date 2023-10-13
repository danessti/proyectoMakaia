package com.example.ProyectoIntegradorMakaia.Entities;

import com.example.ProyectoIntegradorMakaia.Utils.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_reserva;

//    cada cliente puede tener muchas reservas, y cada reserva está asociada a un cliente específico a través de la columna
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "date_hour_reservation", nullable = false)
    private Date dateHourReservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reservation", nullable = false)
    private ReservationStatus reservationStatus;

}