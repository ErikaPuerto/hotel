package com.hotel.repository;

import com.hotel.model.Reserva;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ReservaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    public ReservaRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedJdbc) {

        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // ROW MAPPER

    private final RowMapper<Reserva> rowMapper = (rs, rowNum) -> {

        Reserva reserva = new Reserva();

        reserva.setIdReserva(rs.getInt("id_reserva"));

        reserva.setFechaInicio(
                rs.getDate("fecha_inicio").toLocalDate()
        );

        reserva.setFechaFin(
                rs.getDate("fecha_fin").toLocalDate()
        );

        reserva.setEstado(rs.getString("estado"));

        reserva.setIdCliente(rs.getInt("id_cliente"));
        reserva.setIdHabitacion(rs.getInt("id_habitacion"));
        reserva.setIdEmpleado(rs.getInt("id_empleado"));

        return reserva;
    };

    // LISTAR

    public List<Reserva> findAll() {

        String sql = "SELECT * FROM reserva";

        return jdbcTemplate.query(sql, rowMapper);
    }

    // BUSCAR POR ID

    public Reserva findById(int id) {

        String sql = """
                SELECT *
                FROM reserva
                WHERE id_reserva = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                id
        );
    }

    // INSERTAR

    public int save(Reserva reserva) {

        String sql = """
                INSERT INTO reserva
                (
                    fecha_inicio,
                    fecha_fin,
                    estado,
                    id_cliente,
                    id_habitacion,
                    id_empleado
                )
                VALUES
                (
                    :fechaInicio,
                    :fechaFin,
                    :estado,
                    :idCliente,
                    :idHabitacion,
                    :idEmpleado
                )
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("fechaInicio", reserva.getFechaInicio())
                        .addValue("fechaFin", reserva.getFechaFin())
                        .addValue("estado", reserva.getEstado())
                        .addValue("idCliente", reserva.getIdCliente())
                        .addValue("idHabitacion", reserva.getIdHabitacion())
                        .addValue("idEmpleado", reserva.getIdEmpleado());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_reserva"}
        );

        return keyHolder.getKey().intValue();
    }

    // ACTUALIZAR

    public int update(int id, Reserva reserva) {

        String sql = """
                UPDATE reserva
                SET fecha_inicio = :fechaInicio,
                    fecha_fin = :fechaFin,
                    estado = :estado,
                    id_cliente = :idCliente,
                    id_habitacion = :idHabitacion,
                    id_empleado = :idEmpleado
                WHERE id_reserva = :id
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("fechaInicio", reserva.getFechaInicio())
                        .addValue("fechaFin", reserva.getFechaFin())
                        .addValue("estado", reserva.getEstado())
                        .addValue("idCliente", reserva.getIdCliente())
                        .addValue("idHabitacion", reserva.getIdHabitacion())
                        .addValue("idEmpleado", reserva.getIdEmpleado());

        return namedJdbc.update(sql, params);
    }

    // ELIMINAR

    public int delete(int id) {

        String sql = """
                DELETE FROM reserva
                WHERE id_reserva = :id
                """;

        return namedJdbc.update(
                sql,
                new MapSqlParameterSource("id", id)
        );
    }

    // VERIFICAR DISPONIBILIDAD

    public boolean habitacionDisponible(
            int idHabitacion,
            java.time.LocalDate fechaInicio,
            java.time.LocalDate fechaFin) {

        String sql = """
                SELECT COUNT(*)
                FROM reserva
                WHERE id_habitacion = ?
                AND estado != 'cancelada'
                AND (
                    fecha_inicio <= ?
                    AND fecha_fin >= ?
                )
                """;

        Integer cantidad = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                idHabitacion,
                fechaFin,
                fechaInicio
        );

        return cantidad == 0;
    }
}
