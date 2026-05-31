package com.hotel.service;

import com.hotel.model.Empleado;
import com.hotel.repository.EmpleadoRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<Empleado> listarEmpleados() {
        return repository.findAll();
    }

    public Empleado buscarPorId(int id) {
        return repository.findById(id);
    }

    public int crearEmpleado(Empleado empleado) {
        return repository.save(empleado);
    }

    public int actualizarEmpleado(int id, Empleado empleado) {
        return repository.update(id, empleado);
    }

    public int eliminarEmpleado(int id) {
        return repository.delete(id);
    }
}
