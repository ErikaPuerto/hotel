package com.hotel.repository;

import com.hotel.model.Empleado;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedJdbc;

    public EmpleadoRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedJdbc
    ) {

        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // ROW MAPPER

    private final RowMapper<Empleado> empleadoMapper =
            (rs, rowNum) -> {

        Empleado empleado = new Empleado();

        empleado.setIdEmpleado(
                rs.getInt("id_empleado")
        );

        empleado.setNombre(
                rs.getString("nombre")
        );

        empleado.setIdRol(
                rs.getInt("id_rol")
        );

        empleado.setIdSede(
                rs.getInt("id_sede")
        );

        return empleado;
    };

    // FIND ALL

    public List<Empleado> findAll() {

        String sql = """
                SELECT *
                FROM empleado
                """;

        return jdbcTemplate.query(
                sql,
                empleadoMapper
        );
    }

    // FIND BY ID

    public Empleado findById(int id) {

        String sql = """
                SELECT *
                FROM empleado
                WHERE id_empleado = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                empleadoMapper,
                id
        );
    }

    // SAVE

    public int save(Empleado empleado) {

        String sql = """
                INSERT INTO empleado
                (
                    nombre,
                    id_rol,
                    id_sede
                )
                VALUES
                (
                    :nombre,
                    :idRol,
                    :idSede
                )
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()

                .addValue(
                        "nombre",
                        empleado.getNombre()
                )

                .addValue(
                        "idRol",
                        empleado.getIdRol()
                )

                .addValue(
                        "idSede",
                        empleado.getIdSede()
                );

        KeyHolder keyHolder =
                new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_empleado"}
        );

        return keyHolder
                .getKey()
                .intValue();
    }

    // UPDATE

    public int update(
            int id,
            Empleado empleado
    ) {

        String sql = """
                UPDATE empleado
                SET
                    nombre = :nombre,
                    id_rol = :idRol,
                    id_sede = :idSede
                WHERE id_empleado = :id
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()

                .addValue("id", id)

                .addValue(
                        "nombre",
                        empleado.getNombre()
                )

                .addValue(
                        "idRol",
                        empleado.getIdRol()
                )

                .addValue(
                        "idSede",
                        empleado.getIdSede()
                );

        return namedJdbc.update(
                sql,
                params
        );
    }

    // DELETE

    public int delete(int id) {

        String sql = """
                DELETE FROM empleado
                WHERE id_empleado = :id
                """;

        return namedJdbc.update(
                sql,
                new MapSqlParameterSource(
                        "id",
                        id
                )
        );
    }
}