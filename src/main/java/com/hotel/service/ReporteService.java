package com.hotel.service;

import com.hotel.model.IngresosHotel;
import com.hotel.model.OcupacionHabitacion;
import com.hotel.repository.ReporteRepository;
import com.hotel.model.ReservasPorSede;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteRepository repository;

    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }

    public IngresosHotel obtenerIngresosHotel() {
        return repository.obtenerIngresosHotel();
    }

    public List<OcupacionHabitacion>
    obtenerOcupacionHabitaciones() {

        return repository
                .obtenerOcupacionHabitaciones();
    }

    public List<ReservasPorSede>
    obtenerReservasPorSede() {

        return repository
                .obtenerReservasPorSede();
    }
}