package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Reserva {

    private Integer idReserva;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede exceder los 50 caracteres")
    private String estado;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;
    
    @NotNull(message = "El ID de la habitación es obligatorio")
    private Integer idHabitacion;
    @NotNull(message = "El ID del empleado es obligatorio")
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