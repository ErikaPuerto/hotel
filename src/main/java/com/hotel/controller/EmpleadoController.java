package com.hotel.controller;

import com.hotel.model.Empleado;
import com.hotel.service.EmpleadoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(
            EmpleadoService service
    ) {

        this.service = service;
    }

    // GET ALL

    @GetMapping
    public List<Empleado> listarEmpleados() {

        return service.listarEmpleados();
    }

    // GET BY ID

    @GetMapping("/{id}")
    public Empleado buscarPorId(
            @PathVariable int id
    ) {

        return service.buscarPorId(id);
    }

    // POST

    @PostMapping
    public ResponseEntity<Map<String, Object>>
    crearEmpleado(
            @RequestBody Empleado empleado
    ) {

        int idGenerado =
                service.crearEmpleado(empleado);

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "id",
                idGenerado
        );

        response.put(
                "mensaje",
                "Empleado creado exitosamente"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<String>
    actualizarEmpleado(
            @PathVariable int id,
            @RequestBody Empleado empleado
    ) {

        int filas =
                service.actualizarEmpleado(
                        id,
                        empleado
                );

        if (filas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Empleado actualizado exitosamente"
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminarEmpleado(
            @PathVariable int id
    ) {

        int filas =
                service.eliminarEmpleado(id);

        if (filas == 0) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Empleado eliminado exitosamente"
        );
    }
}