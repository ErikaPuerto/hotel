package com.hotel.service;

import com.hotel.model.Pago;
import com.hotel.repository.PagoRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private final PagoRepository repository;

    public PagoService(PagoRepository repository) {
        this.repository = repository;
    }

    public List<Pago> listarPagos() {
        return repository.findAll();
    }

    public Pago obtenerPago(int id) {
        return repository.findById(id);
    }

    public int crearPago(Pago pago) {
        return repository.save(pago);
    }

    public int actualizarPago(int id, Pago pago) {
        return repository.update(id, pago);
    }

    public int eliminarPago(int id) {
        return repository.delete(id);
    }
}