package com.hotel.repository;

import com.hotel.model.Cliente;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    public ClienteRepository(JdbcTemplate jdbcTemplate,
                             NamedParameterJdbcTemplate namedJdbc) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // RowMapper

    private final RowMapper<Cliente> clienteRowMapper = (rs, rowNum) -> {
        Cliente cliente = new Cliente();

        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setUsuario(rs.getString("usuario"));
        cliente.setDocumento(rs.getString("documento"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setCorreo(rs.getString("correo"));

        return cliente;
    };

    // LISTAR

    public List<Cliente> findAll() {

        String sql = "SELECT * FROM cliente";

        return jdbcTemplate.query(sql, clienteRowMapper);
    }

    // BUSCAR POR ID

    public Cliente findById(int id) {

        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        return jdbcTemplate.queryForObject(
                sql,
                clienteRowMapper,
                id
        );
    }

    // INSERTAR

    public int save(Cliente cliente) {

        String sql = """
                INSERT INTO cliente
                (usuario, documento, telefono, correo)
                VALUES
                (:usuario, :documento, :telefono, :correo)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("usuario", cliente.getUsuario())
                .addValue("documento", cliente.getDocumento())
                .addValue("telefono", cliente.getTelefono())
                .addValue("correo", cliente.getCorreo());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"id_cliente"}
        );

        return keyHolder.getKey().intValue();
    }

    // ACTUALIZAR

    public int update(int id, Cliente cliente) {

        String sql = """
                UPDATE cliente
                SET usuario = :usuario,
                    documento = :documento,
                    telefono = :telefono,
                    correo = :correo
                WHERE id_cliente = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("usuario", cliente.getUsuario())
                .addValue("documento", cliente.getDocumento())
                .addValue("telefono", cliente.getTelefono())
                .addValue("correo", cliente.getCorreo());

        return namedJdbc.update(sql, params);
    }

    // ELIMINAR

    public int delete(int id) {

        String sql = "DELETE FROM cliente WHERE id_cliente = :id";

        return namedJdbc.update(
                sql,
                new MapSqlParameterSource("id", id)
        );
    }
}