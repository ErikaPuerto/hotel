package com.hotel.controller;

import com.hotel.model.IngresosHotel;
import com.hotel.model.OcupacionHabitacion;
import com.hotel.model.ReservasPorSede;
import com.hotel.service.ReporteService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping("/ingresos")
    public IngresosHotel obtenerIngresosHotel() {

        return service.obtenerIngresosHotel();
    }

    @GetMapping("/ocupacion")
    public List<OcupacionHabitacion>
    obtenerOcupacionHabitaciones() {

        return service
                .obtenerOcupacionHabitaciones();
    }
    @GetMapping("/reservas-sede")
    public List<ReservasPorSede>
    obtenerReservasPorSede() {

        return service
                .obtenerReservasPorSede();
    }
}