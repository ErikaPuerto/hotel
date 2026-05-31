package com.hotel.service;

import com.hotel.model.Pago;
import com.hotel.model.Reserva;
import com.hotel.repository.PagoRepository;
import com.hotel.repository.ReservaRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;

    public PagoService(
            PagoRepository pagoRepository,
            ReservaRepository reservaRepository) {

        this.pagoRepository = pagoRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPago(int id) {
        try {
            return pagoRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    // CREAR PAGO — valida que la reserva exista y no esté cancelada
    public int crearPago(Pago pago) {

        Reserva reserva;

        try {
            reserva = reservaRepository.findById(pago.getIdReserva());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "La reserva indicada no existe"
            );
        }

        if ("cancelada".equals(reserva.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede registrar un pago para una reserva cancelada"
            );
        }

        // Verificar que no haya un pago completado ya para esa reserva
        boolean yaExistePagoCompletado = pagoRepository.findAll()
                .stream()
                .anyMatch(p ->
                    p.getIdReserva().equals(pago.getIdReserva()) &&
                    "completado".equals(p.getEstado())
                );

        if (yaExistePagoCompletado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un pago completado para esta reserva"
            );
        }

        pago.setEstado("completado");

        return pagoRepository.save(pago);
    }

    public int actualizarPago(int id, Pago pago) {
        return pagoRepository.update(id, pago);
    }

    public int eliminarPago(int id) {
        return pagoRepository.delete(id);
    }
}
