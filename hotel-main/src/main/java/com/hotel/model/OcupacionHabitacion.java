package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OcupacionHabitacion {

    @NotNull(message = "El ID de la habitación es obligatorio")
    private Integer idHabitacion;

    @NotNull(message = "El número de la habitación es obligatorio")
    private Integer numero;

    @NotBlank(message = "El estado de la habitación es obligatorio")
    @Size(max = 50, message = "El estado de la habitación no puede exceder los 50 caracteres")
    private String estado;

    @NotNull(message = "El total de reservas es obligatorio")
    private Integer totalReservas;

    // GETTERS

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getTotalReservas() {
        return totalReservas;
    }

    // SETTERS

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotalReservas(Integer totalReservas) {
        this.totalReservas = totalReservas;
    }
}