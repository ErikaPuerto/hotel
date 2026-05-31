package com.hotel.service;

import com.hotel.model.Cliente;
import com.hotel.repository.ClienteRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente obtenerCliente(int id) {
        return repository.findById(id);
    }

    public int crearCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public int actualizarCliente(int id, Cliente cliente) {
        return repository.update(id, cliente);
    }

    public int eliminarCliente(int id) {
        return repository.delete(id);
    }
}
