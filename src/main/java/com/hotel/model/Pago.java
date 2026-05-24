package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {

    private Integer idPago;

    private BigDecimal monto;

    private LocalDate fecha;

    private String estado;

    private Integer idMetodo;
    private Integer idReserva;
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