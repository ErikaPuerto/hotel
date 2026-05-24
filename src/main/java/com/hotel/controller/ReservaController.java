package com.hotel.controller;

import com.hotel.model.Reserva;
import com.hotel.service.ReservaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    // LISTAR

    @GetMapping
    public List<Reserva> listarReservas() {
        return service.listarReservas();
    }

    // BUSCAR POR ID

    @GetMapping("/{id}")
    public Reserva obtenerReserva(@PathVariable int id) {
        return service.obtenerReserva(id);
    }

    // CREAR

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearReserva(
            @RequestBody Reserva reserva) {

        int idGenerado = service.crearReserva(reserva);

        Map<String, Object> response = new HashMap<>();

        response.put("id", idGenerado);
        response.put("mensaje", "Reserva creada exitosamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ACTUALIZAR

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarReserva(
            @PathVariable int id,
            @RequestBody Reserva reserva) {

        int filas = service.actualizarReserva(id, reserva);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Reserva actualizada");
    }

    // ELIMINAR

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(
            @PathVariable int id) {

        int filas = service.eliminarReserva(id);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Reserva eliminada");
    }
}