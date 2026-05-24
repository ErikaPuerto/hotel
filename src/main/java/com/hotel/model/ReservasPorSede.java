package com.hotel.model;

public class ReservasPorSede {

    private Integer idSede;

    private String nombreSede;

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