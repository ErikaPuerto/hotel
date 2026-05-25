package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Rol {

    private Integer idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 100, message = "El nombre del rol no puede exceder los 100 caracteres")
    private String nombre;

    // GETTERS

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }

    // SETTERS

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}