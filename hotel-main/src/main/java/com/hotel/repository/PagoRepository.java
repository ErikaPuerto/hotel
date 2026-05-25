package com.hotel.repository;

import com.hotel.model.Pago;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PagoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    public PagoRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedJdbc
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // ROW MAPPER

    private final RowMapper<Pago> rowMapper = (rs, rowNum) -> {

        Pago pago = new Pago();

        pago.setIdPago(rs.getInt("id_pago"));

        pago.setMonto(rs.getBigDecimal("monto"));

        pago.setFecha(
                rs.getDate("fecha").toLocalDate()
        );

        pago.setEstado(rs.getString("estado"));

        pago.setIdMetodo(rs.getInt("id_metodo"));
        pago.setIdReserva(rs.getInt("id_reserva"));
        pago.setIdCliente(rs.getInt("id_cliente"));

        return pago;
    };

    // LISTAR

    public List<Pago> findAll() {

        String sql = "SELECT * FROM pago";

        return jdbcTemplate.query(sql, rowMapper);
    }

    // BUSCAR POR ID

    public Pago findById(int id) {

        String sql = """
                SELECT *
                FROM pago
                WHERE id_pago = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                id
        );
    }

    // INSERTAR

    public int save(Pago pago) {

        String sql = """
                INSERT INTO pago
                (
                    monto,
                    fecha,
                    estado,
                    id_metodo,
                    id_reserva,
                    id_cliente
                )
                VALUES
                (
                    :monto,
                    :fecha,
                    :estado,
                    :idMetodo,
                    :idReserva,
                    :idCliente
                )
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("monto", pago.getMonto())
                        .addValue("fecha", pago.getFecha())
                        .addValue("estado", pago.getEstado())
                        .addValue("idMetodo", pago.getIdMetodo())
                        .addValue("idReserva", pago.getIdReserva())
                        .addValue("idCliente", pago.getIdCliente());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_pago"}
        );

        return keyHolder.getKey().intValue();
    }

    // ACTUALIZAR

    public int update(int id, Pago pago) {

        String sql = """
                UPDATE pago
                SET monto = :monto,
                    fecha = :fecha,
                    estado = :estado,
                    id_metodo = :idMetodo,
                    id_reserva = :idReserva,
                    id_cliente = :idCliente
                WHERE id_pago = :id
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("monto", pago.getMonto())
                        .addValue("fecha", pago.getFecha())
                        .addValue("estado", pago.getEstado())
                        .addValue("idMetodo", pago.getIdMetodo())
                        .addValue("idReserva", pago.getIdReserva())
                        .addValue("idCliente", pago.getIdCliente());

        return namedJdbc.update(sql, params);
    }

    // ELIMINAR

    public int delete(int id) {

        String sql = """
                DELETE FROM pago
                WHERE id_pago = :id
                """;

        return namedJdbc.update(
                sql,
                new MapSqlParameterSource("id", id)
        );
    }
}