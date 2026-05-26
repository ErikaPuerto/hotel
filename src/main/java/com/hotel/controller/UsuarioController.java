package com.hotel.controller;

import com.hotel.model.Usuario;
import com.hotel.service.UsuarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(
            UsuarioService service
    ) {

        this.service = service;
    }

    // GET ALL

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {

        return ResponseEntity.ok(
                service.listarUsuarios()
        );
    }

    // GET BY ID

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable int id
    ) {

        Usuario usuario =
                service.buscarPorId(id);

        if (usuario == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(usuario);
    }

    // POST

    @PostMapping
    public ResponseEntity<Map<String, Object>>
    crearUsuario(
            @RequestBody Usuario usuario
    ) {

        int idGenerado =
                service.crearUsuario(usuario);

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "id",
                idGenerado
        );

        response.put(
                "mensaje",
                "Usuario creado exitosamente"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<String>
    actualizarUsuario(
            @PathVariable int id,
            @RequestBody Usuario usuario
    ) {

        int filas =
                service.actualizarUsuario(
                        id,
                        usuario
                );

        if (filas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Usuario actualizado exitosamente"
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminarUsuario(
            @PathVariable int id
    ) {

        int filas =
                service.eliminarUsuario(id);

        if (filas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Usuario eliminado exitosamente"
        );
    }
}