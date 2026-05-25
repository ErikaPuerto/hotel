package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class Cliente {

    private Integer idCliente;
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 100, message = "El nombre de usuario no puede exceder los 100 caracteres")
    private String usuario;
    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 20, message = "El documento no puede exceder los 20 caracteres")
    private String documento;
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String telefono;
    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo no puede exceder los 100 caracteres")
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