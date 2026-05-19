package com.hotel.service;

import com.hotel.model.Habitacion;
import com.hotel.repository.HabitacionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Habitacion obtenerPorId(int id) {
        return habitacionRepository.findById(id);
    }
    public int crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public int actualizarHabitacion(int id, Habitacion habitacion) {
        return habitacionRepository.update(id, habitacion);
    }

    public int eliminarHabitacion(int id) {
        return habitacionRepository.delete(id);
    }
}