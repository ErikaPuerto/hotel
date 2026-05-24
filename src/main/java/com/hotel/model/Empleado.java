package com.hotel.model;

public class Empleado {

    private Integer idEmpleado;

    private String nombre;

    private String telefono;

    private Integer idCargo;

    private Integer idSede;

    private Integer idUsuario;

    // GETTERS

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    // SETTERS

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}