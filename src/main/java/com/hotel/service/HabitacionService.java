package com.hotel.service;

import com.hotel.model.Habitacion;
import com.hotel.repository.HabitacionRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionService(HabitacionRepository repository) {
        this.repository = repository;
    }

    public List<Habitacion> listarHabitaciones() {
        return repository.findAll();
    }

    public List<Habitacion> listarDisponibles() {
        return repository.findDisponibles();
    }

    public Habitacion obtenerPorId(int id) {
        return repository.findById(id);
    }

    public int crearHabitacion(Habitacion habitacion) {
        return repository.save(habitacion);
    }

    public int actualizarHabitacion(int id, Habitacion habitacion) {
        return repository.update(id, habitacion);
    }

    public int eliminarHabitacion(int id) {
        return repository.delete(id);
    }
}
