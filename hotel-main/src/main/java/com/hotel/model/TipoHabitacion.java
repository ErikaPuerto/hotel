package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TipoHabitacion {

    private Integer idTipo;
    @NotBlank(message = "El nombre del tipo de habitación es obligatorio")
    @Size(max = 100, message = "El nombre del tipo de habitación no puede exceder los 100 caracteres")  
    private String nombreTipo;

    @NotNull(message = "El precio base es obligatorio")
    private BigDecimal precioBase;
    @NotNull(message = "La capacidad es obligatoria")
    private Integer capacidad;

    // GETTERS

    public Integer getIdTipo() {
        return idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    // SETTERS

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}