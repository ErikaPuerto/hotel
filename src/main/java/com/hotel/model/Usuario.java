package com.hotel.model;

public class Usuario {

    private Integer idUsuario;

    private String username;

    private String password;

    private Boolean activo;

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