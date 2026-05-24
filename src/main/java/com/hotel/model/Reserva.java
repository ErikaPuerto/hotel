package com.hotel.model;

import java.time.LocalDate;

public class Reserva {

    private Integer idReserva;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String estado;

    private Integer idCliente;
    private Integer idHabitacion;
    private Integer idEmpleado;

    // GETTERS

    public Integer getIdReserva() {
        return idReserva;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    // SETTERS

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}