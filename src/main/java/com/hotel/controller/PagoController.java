package com.hotel.controller;

import com.hotel.model.Pago;
import com.hotel.service.PagoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    // LISTAR

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(service.listarPagos());
    }

    // BUSCAR POR ID

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable int id) {

        Pago pago = service.obtenerPago(id);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }

    // CREAR

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearPago(
            @RequestBody Pago pago) {

        int idGenerado = service.crearPago(pago);

        Map<String, Object> response = new HashMap<>();

        response.put("id", idGenerado);
        response.put("mensaje", "Pago registrado exitosamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ACTUALIZAR

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPago(
            @PathVariable int id,
            @RequestBody Pago pago) {

        int filas = service.actualizarPago(id, pago);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Pago actualizado");
    }

    // ELIMINAR

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(
            @PathVariable int id) {

        int filas = service.eliminarPago(id);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Pago eliminado");
    }
}