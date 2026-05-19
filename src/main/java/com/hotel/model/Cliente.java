package com.hotel.model;

public class Cliente {

    private Integer idCliente;
    private String usuario;
    private String documento;
    private String telefono;
    private String correo;

    // GETTERS

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    // SETTERS

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}