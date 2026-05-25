package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Habitacion {
    private Integer idHabitacion;
    @NotNull(message = "El número de la habitación es obligatorio")
    private Integer numero;
    @NotBlank(message = "El estado de la habitación es obligatorio")
    @Size(max = 50, message = "El estado de la habitación no puede exceder los 50 caracteres")
    private String estado;
    @NotNull(message = "El ID del tipo de habitación es obligatorio")
    private Integer idTipo;
    @NotNull(message = "El ID de la sede es obligatorio")
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
