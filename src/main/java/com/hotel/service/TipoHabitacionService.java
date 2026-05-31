package com.hotel.service;

import com.hotel.model.TipoHabitacion;
import com.hotel.repository.TipoHabitacionRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TipoHabitacionService {

    private final TipoHabitacionRepository repository;

    public TipoHabitacionService(TipoHabitacionRepository repository) {
        this.repository = repository;
    }

    public List<TipoHabitacion> listarTipos() {
        return repository.findAll();
    }

    public TipoHabitacion obtenerTipo(int id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public int crearTipo(TipoHabitacion tipo) {
        return repository.save(tipo);
    }

    public int actualizarTipo(int id, TipoHabitacion tipo) {
        return repository.update(id, tipo);
    }

    public int eliminarTipo(int id) {
        return repository.delete(id);
    }
}
