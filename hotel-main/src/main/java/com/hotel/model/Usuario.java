package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Usuario {

    private Integer idUsuario;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 100, message = "El nombre de usuario no puede exceder los 100 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 100, message = "La contraseña no puede exceder los 100 caracteres")
    private String password;

    @NotNull(message = "El estado de activo es obligatorio")    
    private Boolean activo;

    @NotNull(message = "El ID del rol es obligatorio")
    private Integer idRol;

    // GETTERS

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Integer getIdRol() {
        return idRol;
    }

    // SETTERS

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
}