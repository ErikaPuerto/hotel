package com.hotel.repository;

import com.hotel.model.TipoHabitacion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TipoHabitacionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    public TipoHabitacionRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedJdbc) {

        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // ROW MAPPER

    private final RowMapper<TipoHabitacion> rowMapper = (rs, rowNum) -> {

        TipoHabitacion tipo = new TipoHabitacion();

        tipo.setIdTipo(rs.getInt("id_tipo"));
        tipo.setNombreTipo(rs.getString("nombre_tipo"));
        tipo.setPrecioBase(rs.getBigDecimal("precio_base"));
        tipo.setCapacidad(rs.getInt("capacidad"));

        return tipo;
    };

    // LISTAR

    public List<TipoHabitacion> findAll() {

        String sql = "SELECT * FROM tipo_habitacion";

        return jdbcTemplate.query(sql, rowMapper);
    }

    // BUSCAR POR ID

    public TipoHabitacion findById(int id) {

        String sql = """
                SELECT *
                FROM tipo_habitacion
                WHERE id_tipo = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                id
        );
    }

    // INSERTAR

    public int save(TipoHabitacion tipo) {

        String sql = """
                INSERT INTO tipo_habitacion
                (nombre_tipo, precio_base, capacidad)
                VALUES
                (:nombreTipo, :precioBase, :capacidad)
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("nombreTipo", tipo.getNombreTipo())
                        .addValue("precioBase", tipo.getPrecioBase())
                        .addValue("capacidad", tipo.getCapacidad());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_tipo"}
        );

        return keyHolder.getKey().intValue();
    }

    // ACTUALIZAR

    public int update(int id, TipoHabitacion tipo) {

        String sql = """
                UPDATE tipo_habitacion
                SET nombre_tipo = :nombreTipo,
                    precio_base = :precioBase,
                    capacidad = :capacidad
                WHERE id_tipo = :id
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("nombreTipo", tipo.getNombreTipo())
                        .addValue("precioBase", tipo.getPrecioBase())
                        .addValue("capacidad", tipo.getCapacidad());

        return namedJdbc.update(sql, params);
    }

    // ELIMINAR

    public int delete(int id) {

        String sql = """
                DELETE FROM tipo_habitacion
                WHERE id_tipo = :id
                """;

        return namedJdbc.update(
                sql,
                new MapSqlParameterSource("id", id)
        );
    }
}