package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airport;
import com.example.ProyectoIntegradorMakaia.Services.AirportService;
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

@RestController
@RequestMapping("/v1/airports")
@Api(tags = "Airports", description = "Airport Controller")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get all Airports", notes = "Retrieve the list of all airports.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aeropuertos recuperados exitosamente."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "No se encontraron aeropuertos.")
    })
//     Endpoint para obtener todos los aeropuertos
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Register an Airplane", notes = "An Airport object is received in the request body, " +
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
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get Airport by Id", notes = "Retrieves an Airport by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición se proceso correctamente"),
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
//    Endpoint para obtener un aeropuerto por su id
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);

        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Update an Airport by Id", notes = "Update an existing Airport in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aeropuerto actualizado correctamente."),
            @ApiResponse(code = 201, message = "Creado correctamente"),
            @ApiResponse(code = 400, message = "Solicitud incorrecta, contiene datos incorrectos o invalidos que impiden " +
                    "la actualización."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport updateAirport) {
        Airport airport = airportService.updateAirport(id, updateAirport);

        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Delete an Airport by Id", notes = "Delete an existing Airport in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición se proceso correctamente"),
            @ApiResponse(code = 204, message = "El recurso se eliminó con éxito."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "El recurso con el Id especificado no fue encontrado en la base de datos.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}