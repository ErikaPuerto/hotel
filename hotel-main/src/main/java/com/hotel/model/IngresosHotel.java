package com.hotel.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class IngresosHotel {
    @NotNull(message = "El total de pagos es obligatorio")
    private Integer totalPagos;
    @NotNull(message = "Los ingresos totales son obligatorios")
    private BigDecimal ingresosTotales;
    @NotNull(message = "El promedio de pago es obligatorio")
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