package com.hotel.service;

import com.hotel.model.Habitacion;
import com.hotel.model.Reserva;
import com.hotel.repository.HabitacionRepository;
import com.hotel.repository.ReservaRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            HabitacionRepository habitacionRepository) {

        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReserva(int id) {
        try {
            return reservaRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    // CREAR — valida disponibilidad antes de insertar
    public int crearReserva(Reserva reserva) {

        boolean disponible = reservaRepository.habitacionDisponible(
                reserva.getIdHabitacion(),
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );

        if (!disponible) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La habitación no está disponible para las fechas seleccionadas"
            );
        }

        reserva.setEstado("pendiente");

        return reservaRepository.save(reserva);
    }

    public int actualizarReserva(int id, Reserva reserva) {
        return reservaRepository.update(id, reserva);
    }

    // CHECK-IN — cambia estado reserva a "activa" y habitación a "ocupada"
    public void realizarCheckIn(int idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (reserva == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reserva no encontrada"
            );
        }

        if (!"pendiente".equals(reserva.getEstado()) &&
            !"confirmada".equals(reserva.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Solo se puede hacer check-in en reservas pendientes o confirmadas"
            );
        }

        // Actualizar estado de la reserva
        reserva.setEstado("activa");
        reservaRepository.update(idReserva, reserva);

        // Actualizar estado de la habitación a ocupada
        Habitacion habitacion =
                habitacionRepository.findById(reserva.getIdHabitacion());
        habitacion.setEstado("ocupada");
        habitacionRepository.update(habitacion.getIdHabitacion(), habitacion);
    }

    // CHECK-OUT — cambia estado reserva a "completada" y habitación a "disponible"
    public void realizarCheckOut(int idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (reserva == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reserva no encontrada"
            );
        }

        if (!"activa".equals(reserva.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Solo se puede hacer check-out en reservas activas"
            );
        }

        // Actualizar estado de la reserva
        reserva.setEstado("completada");
        reservaRepository.update(idReserva, reserva);

        // Liberar la habitación
        Habitacion habitacion =
                habitacionRepository.findById(reserva.getIdHabitacion());
        habitacion.setEstado("disponible");
        habitacionRepository.update(habitacion.getIdHabitacion(), habitacion);
    }

    // CANCELAR — cambia estado reserva y libera habitación si estaba ocupada
    public void cancelarReserva(int idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (reserva == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reserva no encontrada"
            );
        }

        if ("completada".equals(reserva.getEstado()) ||
            "cancelada".equals(reserva.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede cancelar una reserva ya completada o cancelada"
            );
        }

        // Si estaba activa, liberar habitación
        if ("activa".equals(reserva.getEstado())) {
            Habitacion habitacion =
                    habitacionRepository.findById(reserva.getIdHabitacion());
            habitacion.setEstado("disponible");
            habitacionRepository.update(habitacion.getIdHabitacion(), habitacion);
        }

        reserva.setEstado("cancelada");
        reservaRepository.update(idReserva, reserva);
    }

    public int eliminarReserva(int id) {
        return reservaRepository.delete(id);
    }
}
