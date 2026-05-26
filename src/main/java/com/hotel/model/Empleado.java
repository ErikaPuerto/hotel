package com.hotel.model;

public class Empleado {

    private Integer idEmpleado;

    private String nombre;

    private Integer idRol;

    private Integer idSede;

    // GETTERS

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public Integer getIdSede() {
        return idSede;
    }

    // SETTERS

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
}