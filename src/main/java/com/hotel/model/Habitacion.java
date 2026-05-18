package com.hotel.model;

public class Habitacion {
    private Integer idHabitacion;
    private Integer numero;
    private String estado;
    private Integer idTipo;
    private Integer idSede;

    //Getters
    public Integer getIdHabitacion() {
        return idHabitacion;
    }
    public Integer getNumero() {
        return numero;
    }
    public String getEstado() {
        return estado;
    }
    public Integer getIdTipo() {
        return idTipo;
    }
    public Integer getIdSede() {
        return idSede;
    }
    
    //Setters
    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
    
}
