package com.hotel.controller;

import com.hotel.model.Habitacion;
import com.hotel.service.HabitacionService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(
            HabitacionService habitacionService
    ) {

        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> listarHabitaciones() {

        return habitacionService.listarHabitaciones();
    }

    @GetMapping("/disponibles")
    public List<Habitacion> listarDisponibles() {

        return habitacionService.listarDisponibles();
    }

    @GetMapping("/{id}")
    public Habitacion obtenerHabitacion(
            @PathVariable int id
    ) {

        return habitacionService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>>
    crearHabitacion(
            @Valid @RequestBody Habitacion habitacion
    ) {

        int idGenerado =
                habitacionService.crearHabitacion(
                        habitacion
                );

        Map<String, Object> response =
                new HashMap<>();

        response.put("id", idGenerado);

        response.put(
                "mensaje",
                "Habitación creada exitosamente"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>
    actualizarHabitacion(
            @PathVariable int id,
            @Valid @RequestBody Habitacion habitacion
    ) {

        int filasAfectadas =
                habitacionService.actualizarHabitacion(
                        id,
                        habitacion
                );

        if (filasAfectadas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Habitación actualizada exitosamente"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminarHabitacion(
            @PathVariable int id
    ) {

        int filasAfectadas =
                habitacionService.eliminarHabitacion(id);

        if (filasAfectadas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Habitación eliminada exitosamente"
        );
    }
}