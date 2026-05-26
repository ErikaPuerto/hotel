package com.hotel.model;

public class Rol {

    private Integer idRol;

    private String nombreRol;

    private String descripcion;

    // GETTERS

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // SETTERS

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}