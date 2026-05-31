package com.hotel.service;

import com.hotel.model.Usuario;
import com.hotel.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(int id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public int crearUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public int actualizarUsuario(int id, Usuario usuario) {
        return repository.update(id, usuario);
    }

    public int eliminarUsuario(int id) {
        return repository.delete(id);
    }
}
