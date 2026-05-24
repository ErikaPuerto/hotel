package com.hotel.repository;

import com.hotel.model.Usuario;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedJdbc;

    public UsuarioRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedJdbc
    ) {

        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // ROW MAPPER

    private final RowMapper<Usuario> usuarioMapper =
            (rs, rowNum) -> {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(
                rs.getInt("id_usuario")
        );

        usuario.setUsername(
                rs.getString("username")
        );

        usuario.setPassword(
                rs.getString("password")
        );

        usuario.setActivo(
                rs.getBoolean("activo")
        );

        usuario.setIdRol(
                rs.getInt("id_rol")
        );

        return usuario;
    };

    // FIND ALL

    public List<Usuario> findAll() {

        String sql = """
                SELECT *
                FROM usuario
                """;

        return jdbcTemplate.query(
                sql,
                usuarioMapper
        );
    }

    // FIND BY ID

    public Usuario findById(int id) {

        String sql = """
                SELECT *
                FROM usuario
                WHERE id_usuario = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                usuarioMapper,
                id
        );
    }

    // SAVE

    public int save(Usuario usuario) {

        String sql = """
                INSERT INTO usuario
                (
                    username,
                    password,
                    activo,
                    id_rol
                )
                VALUES
                (
                    :username,
                    :password,
                    :activo,
                    :idRol
                )
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()

                .addValue(
                        "username",
                        usuario.getUsername()
                )

                .addValue(
                        "password",
                        usuario.getPassword()
                )

                .addValue(
                        "activo",
                        usuario.getActivo()
                )

                .addValue(
                        "idRol",
                        usuario.getIdRol()
                );

        KeyHolder keyHolder =
                new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_usuario"}
        );

        return keyHolder.getKey().intValue();
    }

    // UPDATE

    public int update(
            int id,
            Usuario usuario
    ) {

        String sql = """
                UPDATE usuario
                SET
                    username = :username,
                    password = :password,
                    activo = :activo,
                    id_rol = :idRol
                WHERE id_usuario = :id
                """;

        MapSqlParameterSource params =
                new MapSqlParameterSource()

                .addValue("id", id)

                .addValue(
                        "username",
                        usuario.getUsername()
                )

                .addValue(
                        "password",
                        usuario.getPassword()
                )

                .addValue(
                        "activo",
                        usuario.getActivo()
                )

                .addValue(
                        "idRol",
                        usuario.getIdRol()
                );

        return namedJdbc.update(
                sql,
                params
        );
    }

    // DELETE

    public int delete(int id) {

        String sql = """
                DELETE FROM usuario
                WHERE id_usuario = :id
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