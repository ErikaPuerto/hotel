package com.hotel.service;

import com.hotel.model.Usuario;
import com.hotel.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(
            UsuarioRepository repository
    ) {

        this.repository = repository;
    }

    // LISTAR

    public List<Usuario> listarUsuarios() {

        return repository.findAll();
    }

    // BUSCAR POR ID

    public Usuario buscarPorId(int id) {

        return repository.findById(id);
    }

    // CREAR

    public int crearUsuario(
            Usuario usuario
    ) {

        return repository.save(usuario);
    }

    // ACTUALIZAR

    public int actualizarUsuario(
            int id,
            Usuario usuario
    ) {

        return repository.update(
                id,
                usuario
        );
    }

    // ELIMINAR

    public int eliminarUsuario(int id) {

        return repository.delete(id);
    }
}