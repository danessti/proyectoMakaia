package com.example.ProyectoIntegradorMakaia.Controllers;

import com.example.ProyectoIntegradorMakaia.Entities.Airplane;
import com.example.ProyectoIntegradorMakaia.Services.AirplaneService;
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
@RequestMapping("/v1/airplanes")
@Api(tags = "Airplanes", description = "Airplane Controller")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get all Airplanes", notes = "Retrieve the list of all airplanes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aviones recuperados exitosamente."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "No se encontraron aviones.")
    })
    @GetMapping
    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        List<Airplane> airplanes = airplaneService.getAllAirplanes();
        return new ResponseEntity<>(airplanes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Register an Airplane", notes = "An Airplane object is received in the request body, " +
            "and it is registered in the database")
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
            @ApiResponse(code = 500, message = "Error inesperado del sistema."),
            @ApiResponse(code = 503, message = "El servidor no puede manejar la solicitud en este momento debido a una sobrecarga " +
                    "o mantenimiento. Intenté de nuevo más tarde!")
    })
    @PostMapping
    public ResponseEntity<Airplane> createAirplane(@RequestBody Airplane airplane) {
        Airplane newAirplane = airplaneService.createAirplane(airplane);
        return new ResponseEntity<>(newAirplane, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('READ', 'WRITE')")
    @ApiOperation(value = "Get Airplane By Id", notes = "Retrieves an Airplane by its unique ID.")
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
    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
        Airplane airplane = airplaneService.getAirplaneById(id);

        if (airplane != null) {
            return new ResponseEntity<>(airplane, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Update an Airplane by Id", notes = "Update an existing Airplane in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avión actualizado correctamente."),
            @ApiResponse(code = 201, message = "Creado correctamente"),
            @ApiResponse(code = 400, message = "Solicitud incorrecta, contiene datos incorrectos o invalidos que impiden " +
                    "la actualización."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "El servidor no ha encontrado el recurso solicitado por el usuario.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Long id, @RequestBody Airplane updateAirplane) {
        Airplane airplane = airplaneService.updateAirplane(id, updateAirplane);

        if (airplane != null) {
            return new ResponseEntity<>(airplane, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Delete an Airplane by Id", notes = "Delete an existing Airplane in the database by its unique Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición se proceso correctamente"),
            @ApiResponse(code = 204, message = "El recurso se eliminó con éxito."),
            @ApiResponse(code = 401, message = "Acceso denegado por falta de autorización."),
            @ApiResponse(code = 403, message = "No tiene permisos para ingresar a este endpoint"),
            @ApiResponse(code = 404, message = "El recurso con el Id especificado no fue encontrado en la base de datos.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Airplane> deleteAirplane(@PathVariable Long id) {
        airplaneService.deleteAirplane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}