package com.hotel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReservasPorSede {

    private Integer idSede;

    @NotBlank(message = "El nombre de la sede es obligatorio")
    @Size(max = 100, message = "El nombre de la sede no puede exceder los 100 caracteres")
    private String nombreSede;

    @NotNull(message = "El total de reservas es obligatorio")
    private Integer totalReservas;

    // GETTERS

    public Integer getIdSede() {
        return idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public Integer getTotalReservas() {
        return totalReservas;
    }

    // SETTERS

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public void setTotalReservas(Integer totalReservas) {
        this.totalReservas = totalReservas;
    }
}