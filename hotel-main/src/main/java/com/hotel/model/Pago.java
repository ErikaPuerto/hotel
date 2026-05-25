package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {

    private Integer idPago;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal monto;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede exceder los 50 caracteres")
    private String estado;

    @NotNull(message = "El ID del método de pago es obligatorio")
    private Integer idMetodo;
    @NotNull(message = "El ID de la reserva es obligatorio")
    private Integer idReserva;
    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;

    // GETTERS

    public Integer getIdPago() {
        return idPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getIdMetodo() {
        return idMetodo;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    // SETTERS

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdMetodo(Integer idMetodo) {
        this.idMetodo = idMetodo;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}