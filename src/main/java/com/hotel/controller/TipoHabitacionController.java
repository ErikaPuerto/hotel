package com.hotel.controller;

import com.hotel.model.TipoHabitacion;
import com.hotel.service.TipoHabitacionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-habitacion")
public class TipoHabitacionController {

    private final TipoHabitacionService service;

    public TipoHabitacionController(TipoHabitacionService service) {
        this.service = service;
    }

    // LISTAR

    @GetMapping
    public List<TipoHabitacion> listarTipos() {
        return service.listarTipos();
    }

    // BUSCAR POR ID

    @GetMapping("/{id}")
    public TipoHabitacion obtenerTipo(@PathVariable int id) {
        return service.obtenerTipo(id);
    }

    // CREAR

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearTipo(
            @RequestBody TipoHabitacion tipo) {

        int idGenerado = service.crearTipo(tipo);

        Map<String, Object> response = new HashMap<>();

        response.put("id", idGenerado);
        response.put("mensaje", "Tipo de habitación creado");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ACTUALIZAR

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTipo(
            @PathVariable int id,
            @RequestBody TipoHabitacion tipo) {

        int filas = service.actualizarTipo(id, tipo);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Tipo actualizado");
    }

    // ELIMINAR

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipo(
            @PathVariable int id) {

        int filas = service.eliminarTipo(id);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Tipo eliminado");
    }
}