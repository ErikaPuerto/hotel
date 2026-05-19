package com.hotel.service;

import com.hotel.model.Cliente;
import com.hotel.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerCliente(int id) {
        return clienteRepository.findById(id);
    }

    public int crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public int actualizarCliente(int id, Cliente cliente) {
        return clienteRepository.update(id, cliente);
    }

    public int eliminarCliente(int id) {
        return clienteRepository.delete(id);
    }
}