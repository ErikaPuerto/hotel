package com.hotel.repository;

import com.hotel.model.IngresosHotel;
import com.hotel.model.OcupacionHabitacion;
import com.hotel.model.ReservasPorSede;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReporteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ROW MAPPER

    private final RowMapper<IngresosHotel> ingresosMapper =
            (rs, rowNum) -> {

        IngresosHotel ingresos = new IngresosHotel();

        ingresos.setTotalPagos(
                rs.getInt("total_pagos")
        );

        ingresos.setIngresosTotales(
                rs.getBigDecimal("ingresos_totales")
        );

        ingresos.setPromedioPago(
                rs.getBigDecimal("promedio_pago")
        );

        return ingresos;
    };

    private final RowMapper<OcupacionHabitacion>
        ocupacionMapper = (rs, rowNum) -> {

    OcupacionHabitacion ocupacion =
            new OcupacionHabitacion();

    ocupacion.setIdHabitacion(
            rs.getInt("id_habitacion")
    );

    ocupacion.setNumero(
            rs.getInt("numero")
    );

    ocupacion.setEstado(
            rs.getString("estado")
    );

    ocupacion.setTotalReservas(
            rs.getInt("total_reservas")
    );

    return ocupacion;
};
private final RowMapper<ReservasPorSede>
        reservasSedeMapper = (rs, rowNum) -> {

    ReservasPorSede reporte =
            new ReservasPorSede();

    reporte.setIdSede(
            rs.getInt("id_sede")
    );

    reporte.setNombreSede(
            rs.getString("nombre_sede")
    );

    reporte.setTotalReservas(
            rs.getInt("total_reservas")
    );

    return reporte;
};

public List<ReservasPorSede>
obtenerReservasPorSede() {

    String sql = """
            SELECT *
            FROM vista_reservas_por_sede
            """;

    return jdbcTemplate.query(
            sql,
            reservasSedeMapper
    );
}
public List<OcupacionHabitacion>
obtenerOcupacionHabitaciones() {

    String sql = """
            SELECT *
            FROM vista_ocupacion_habitaciones
            """;

    return jdbcTemplate.query(
            sql,
            ocupacionMapper
    );
}
    // CONSULTAR VIEW

    public IngresosHotel obtenerIngresosHotel() {

        String sql = """
                SELECT *
                FROM vista_ingresos_hotel
                """;

        return jdbcTemplate.queryForObject(
                sql,
                ingresosMapper
        );
    }
}