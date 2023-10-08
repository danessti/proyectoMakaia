package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airline;
import com.example.ProyectoIntegradorMakaia.Services.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/airlines")
@Api(tags = "Airlines", description = "Airline Controller")
public class AirlineController {

    @Autowired
    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = Objects.requireNonNull(airlineService, "airlineService must not be null");
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get all Airlines", notes = "Retrieve the list of all airlines.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aerolíneas recuperadas exitosamente."),
            @ApiResponse(code = 404, message = "No se encontraron aerolíneas.")
    })
    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airlines = airlineService.getAllAirlines();

        if (airlines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }

//    solo usuario con este rol 'write' pueden acceder a este método
    @PreAuthorize("hasRole('WRITE')")
//    esta anotación se usa para documentar la operación que se está realizando
//    Se recibe por el body un objeto de Aerolínea y este se registra en la base de datos
    @ApiOperation(value = "Register an Airline", notes = "An Airline object is received in the request body, " +
            "and it is registered in the database.")
//    esta anotación se usa para documentar las posibles respuestas que se pueden generar
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se hizo el registro correctamente."),
            @ApiResponse(code = 400, message = "Solicitud Incorrecta, el servidor no pudo entender o procesar la solicitud del usuario " +
                    "debido a un error en la sintaxis."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message =  "Tienes acceso pero no puedes realizar acciones."),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario."),
            @ApiResponse(code = 409, message = "Conflicto de estado existente, intentando crear un aerolínea que ya existe."),
            @ApiResponse(code = 422, message = "no se puede procesar la solicitud debido a problemas en los datos " +
                    "proporcionados en el cuerpo de la solicitud."),
            @ApiResponse(code = 429, message = "Ha excedido la tasa de solicitudes permitidas."),
//            el error 500 puede ser de lógica del servidor o problemas en la base de datos
            @ApiResponse(code = 500, message = "Error inesperado del sistema."),
            @ApiResponse(code = 503, message = "El servidor no puede manejar la solicitud en este momento debido a una sobrecarga " +
                    "o mantenimiento. Intenté de nuevo más tarde!")
    })
    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        Airline newAirline = airlineService.createAirline(airline);
        return new ResponseEntity<>(newAirline, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get Airline By Id", notes = "Retrieves an Airline by its unique ID.")
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
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id) {
        Airline airline = airlineService.getAirlineById(id);

        if (airline != null) {
            return new ResponseEntity<>(airline, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Update an Airline by Id", notes = "Update an existing Airline in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aerolínea actualizada correctamente."),
           @ApiResponse(code = 400, message = "Solicitud incorrecta, contiene datos incorrectos o invalidos que impiden " +
                   "la actualización."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable Long id, @RequestBody Airline updateAirline) {
        Airline airline = airlineService.updateAirline(id, updateAirline);

        if (airline != null) {
            return new ResponseEntity<>(airline, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Delete an Airline by Id", notes = "Delete an existing Airline in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "El recurso se eliminó con éxito."),
            @ApiResponse(code = 404, message = "El recurso con el Id especificado no fue encontrado en la base de datos.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Airline> deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}