package com.hotel.model;

public class OcupacionHabitacion {

    private Integer idHabitacion;

    private Integer numero;

    private String estado;

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