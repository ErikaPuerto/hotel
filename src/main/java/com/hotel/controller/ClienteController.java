package com.hotel.controller;

import com.hotel.model.Cliente;
import com.hotel.service.ClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // LISTAR

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // BUSCAR POR ID

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable int id) {
        return clienteService.obtenerCliente(id);
    }

    // CREAR

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearCliente(
            @RequestBody Cliente cliente) {

        int idGenerado = clienteService.crearCliente(cliente);

        Map<String, Object> response = new HashMap<>();

        response.put("id", idGenerado);
        response.put("mensaje", "Cliente creado exitosamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ACTUALIZAR

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(
            @PathVariable int id,
            @RequestBody Cliente cliente) {

        int filas = clienteService.actualizarCliente(id, cliente);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Cliente actualizado exitosamente");
    }

    // ELIMINAR

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {

        int filas = clienteService.eliminarCliente(id);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }
}