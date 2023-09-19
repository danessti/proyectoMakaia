package com.example.ProyectoIntegradorMakaia.Entities;

import com.example.ProyectoIntegradorMakaia.Utils.ReservationStatus;
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
@Table(name = "reserva")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_reserva;

//    cada cliente puede tener muchas reservas, y cada reserva está asociada a un cliente específico a través de la columna
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "reservation_flight",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private Set<Flight> flights = new HashSet<>();

    @Column(nullable = false)
    private Date fecha_hora_reserva;

//    @OneToOne
//    @JoinColumn(name = "id_estado_reserva", nullable = false)
//    private Estados_reserva estado_reserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reservation", nullable = false)
    private ReservationStatus reservationStatus;

}