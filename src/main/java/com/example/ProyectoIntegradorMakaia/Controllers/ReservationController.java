package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Reservation;
import com.example.ProyectoIntegradorMakaia.Services.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/reservations")
@Api(tags = "Reservation", description = "Reservation Controller")
public class ReservationController {

    private final ReservationService service;

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get all reservations", notes = "Retrieve the list of all reservations.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservas recuperadas exitosamente."),
            @ApiResponse(code = 404, message = "No se encontraron reservas.")
    })
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = service.getAllReservations();

        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Register a reservation", notes = "A reservation object is received in the request body, " +
            "and it is registered in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se hizo el registro correctamente."),
            @ApiResponse(code = 400, message = "Solicitud Incorrecta, el servidor no pudo entender o procesar la solicitud del usuario " +
                    "debido a un error en la sintaxis."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message =  "Tienes acceso pero no puedes realizar acciones."),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario."),
            @ApiResponse(code = 409, message = "Conflicto de estado existente, intentando crear un aeropuerto que ya existe."),
            @ApiResponse(code = 422, message = "no se puede procesar la solicitud debido a problemas en los datos " +
                    "proporcionados en el cuerpo de la solicitud."),
            @ApiResponse(code = 429, message = "Ha excedido la tasa de solicitudes permitidas."),
            @ApiResponse(code = 500, message = "Error inesperado del sistema."),
            @ApiResponse(code = 503, message = "El servidor no puede manejar la solicitud en este momento debido a una sobrecarga " +
                    "o mantenimiento. Intenté de nuevo más tarde!")
    })
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = service.createReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get reservation by Id", notes = "Retrieves a reservation by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Solicitud procesada correctamente pero sin contenido para devolver en la respuesta."),
            @ApiResponse(code = 301, message = "La URL a la que intentas acceder se ha movido permanentemente a otra URL."),
            @ApiResponse(code = 302, message = "La URL a la que intentas acceder se ha movido temporalmente a otra URL."),
            @ApiResponse(code = 304, message = "El recurso solicitado no ha cambiado desde la última vez que lo solicitó."),
            @ApiResponse(code = 400, message = "Solicitud Incorrecta, el servidor no pudo entender o procesar la solicitud del usuario " +
                    "debido a un error en la sintaxis."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message =  "Tienes acceso pero no puedes realizar acciones."),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario."),
            @ApiResponse(code = 405, message = "HTTP no permitido para acceder al recurso."),
            @ApiResponse(code = 406, message = "Solicitud de formato no puede ser proporcionado por el servidor."),
            @ApiResponse(code = 429, message = "Ha excedido la tasa de solicitudes permitidas."),
            @ApiResponse(code = 500, message = "Error inesperado del sistema."),
            @ApiResponse(code = 503, message = "El servidor no puede manejar la solicitud en este momento debido a una sobrecarga " +
                    "o mantenimiento. Intenté de nuevo más tarde!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getAllReservationById(@PathVariable Long id) {
        Reservation reservation = service.getReservationById(id);

        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Update a reservation by Id", notes = "Update an existing reservation in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reserva actualizada correctamente."),
            @ApiResponse(code = 400, message = "Solicitud incorrecta, contiene datos incorrectos o invalidos que impiden " +
                    "la actualización."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updateReservation) {
        Reservation reservation = service.updateReservation(id, updateReservation);

        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Delete a reservation by Id", notes = "Delete an existing reservation in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "El recurso se eliminó con éxito."),
            @ApiResponse(code = 404, message = "El recurso con el Id especificado no fue encontrado en la base de datos.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}