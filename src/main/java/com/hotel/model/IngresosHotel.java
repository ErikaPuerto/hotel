package com.hotel.model;

import java.math.BigDecimal;

public class IngresosHotel {

    private Integer totalPagos;

    private BigDecimal ingresosTotales;

    private BigDecimal promedioPago;

    // GETTERS

    public Integer getTotalPagos() {
        return totalPagos;
    }

    public BigDecimal getIngresosTotales() {
        return ingresosTotales;
    }

    public BigDecimal getPromedioPago() {
        return promedioPago;
    }

    // SETTERS

    public void setTotalPagos(Integer totalPagos) {
        this.totalPagos = totalPagos;
    }

    public void setIngresosTotales(BigDecimal ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public void setPromedioPago(BigDecimal promedioPago) {
        this.promedioPago = promedioPago;
    }
}