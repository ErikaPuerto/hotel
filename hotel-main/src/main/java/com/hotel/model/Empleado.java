package com.hotel.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Empleado {

    private Integer idEmpleado;

    @NotBlank(message = "El nombre del empleado es obligatorio")
    @Size(max = 100, message = "El nombre del empleado no puede exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono del empleado es obligatorio")
    @Size(max = 20, message = "El teléfono del empleado no puede exceder los 20 caracteres")
    private String telefono;

    @NotNull(message = "El ID del cargo es obligatorio")
    private Integer idCargo;

    @NotNull(message = "El ID de la sede es obligatorio")
    private Integer idSede;

    @NotNull(message = "El ID del usuario es obligatorio")
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