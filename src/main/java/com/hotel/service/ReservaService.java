package com.hotel.service;

import com.hotel.model.Habitacion;
import com.hotel.model.Reserva;
import com.hotel.repository.HabitacionRepository;
import com.hotel.repository.ReservaRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository repository;
private final HabitacionRepository habitacionRepository;

public ReservaService(
        ReservaRepository repository,
        HabitacionRepository habitacionRepository
) {
    this.repository = repository;
    this.habitacionRepository = habitacionRepository;
}
    public List<Reserva> listarReservas() {
        return repository.findAll();
    }

    public Reserva obtenerReserva(int id) {
        return repository.findById(id);
    }

    public int crearReserva(Reserva reserva) {

    boolean disponible = repository.habitacionDisponible(
            reserva.getIdHabitacion(),
            reserva.getFechaInicio(),
            reserva.getFechaFin()
    );

    if (!disponible) {
        throw new RuntimeException(
                "La habitación no está disponible en esas fechas"
        );
    }

    // cambiar estado habitación

    Habitacion habitacion =
            habitacionRepository.findById(
                    reserva.getIdHabitacion()
            );

    habitacion.setEstado("ocupada");

    habitacionRepository.update(
            habitacion.getIdHabitacion(),
            habitacion
    );

    return repository.save(reserva);
}

    public int actualizarReserva(int id, Reserva reserva) {
        return repository.update(id, reserva);
    }

    public int eliminarReserva(int id) {
        return repository.delete(id);
    }
}