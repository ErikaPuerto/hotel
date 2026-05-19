package com.hotel.controller;

import com.hotel.model.Habitacion;
import com.hotel.service.HabitacionService;
import com.hotel.repository.HabitacionRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final HabitacionRepository habitacionRepository;

    public HabitacionController(HabitacionService habitacionService,
                                HabitacionRepository habitacionRepository) {
        this.habitacionService = habitacionService;
        this.habitacionRepository = habitacionRepository;
    }

    @GetMapping
    public List<Habitacion> listarHabitaciones() {
        return habitacionService.listarHabitaciones();
    }

    @GetMapping("/{id}")
    public Habitacion obtenerHabitacion(@PathVariable int id) {
        return habitacionService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearHabitacion(@RequestBody Habitacion habitacion) {

        int idGenerado = habitacionRepository.save(habitacion);

        Map<String, Object> response = new HashMap<>();
        response.put("id", idGenerado);
        response.put("mensaje", "Habitación creada exitosamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHabitacion(@PathVariable int id,
                                                       @RequestBody Habitacion habitacion) {

        int filasAfectadas = habitacionRepository.update(id, habitacion);

        if (filasAfectadas == 0)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Habitación actualizada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable int id) {

        int filasAfectadas = habitacionRepository.delete(id);

        if (filasAfectadas == 0)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Habitación eliminada exitosamente");
    }
}