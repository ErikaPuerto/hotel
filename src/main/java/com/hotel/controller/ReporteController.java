package com.hotel.controller;

import com.hotel.model.IngresosHotel;
import com.hotel.model.OcupacionHabitacion;
import com.hotel.model.ReservasPorSede;
import com.hotel.service.ReporteService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    // INGRESOS HOTEL

    @GetMapping("/ingresos")
    public ResponseEntity<IngresosHotel> obtenerIngresosHotel() {

        return ResponseEntity.ok(
                service.obtenerIngresosHotel()
        );
    }

    // OCUPACIÓN HABITACIONES

    @GetMapping("/ocupacion")
    public ResponseEntity<List<OcupacionHabitacion>>
    obtenerOcupacionHabitaciones() {

        return ResponseEntity.ok(
                service.obtenerOcupacionHabitaciones()
        );
    }

    // RESERVAS POR SEDE

    @GetMapping("/reservas-sede")
    public ResponseEntity<List<ReservasPorSede>>
    obtenerReservasPorSede() {

        return ResponseEntity.ok(
                service.obtenerReservasPorSede()
        );
    }
}