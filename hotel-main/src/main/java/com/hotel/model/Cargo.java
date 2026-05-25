package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cargo {

    private Integer idCargo;

    @NotBlank(message = "El nombre del cargo es obligatorio")
    @Size(max = 100, message = "El nombre del cargo no puede exceder los 100 caracteres")
    private String nombre;

    // GETTERS

    public Integer getIdCargo() {
        return idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    // SETTERS

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}