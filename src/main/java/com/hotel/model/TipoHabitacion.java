package com.hotel.model;

import java.math.BigDecimal;

public class TipoHabitacion {

    private Integer idTipo;
    private String nombreTipo;
    private BigDecimal precioBase;
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