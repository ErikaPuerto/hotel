package com.hotel.repository;
import com.hotel.model.Habitacion;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HabitacionRepository {
    // Spring inyecta JdbcTemplate automáticamente (inyección por constructor)
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    public HabitacionRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbc) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = namedJdbc;
    }

    // RowMapper: traduce cada fila a un objeto Habitacion
    private final RowMapper<Habitacion> habitacionRowMapper = (rs, rowNum) -> {
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
        habitacion.setNumero(rs.getInt("numero"));
        habitacion.setEstado(rs.getString("estado"));
        habitacion.setIdTipo(rs.getInt("id_tipo"));
        habitacion.setIdSede(rs.getInt("id_sede"));
        return habitacion;
    };

    // findALL: obtiene todas las habitaciones de la tabla
    public List<Habitacion> findAll() {
        String sql = "SELECT * FROM habitacion";
        return jdbcTemplate.query(sql, habitacionRowMapper);
    }

    public Habitacion findById(int id) {

        String sql = """
                SELECT *
                FROM habitacion
                WHERE id_habitacion = ?
                """;
    
        return jdbcTemplate.queryForObject(
                sql,
                habitacionRowMapper,
                id
        );
    } 

    public List<Habitacion> findDisponibles() {

        String sql = """
                SELECT *
                FROM vista_habitaciones_disponibles
                """;
    
        return jdbcTemplate.query(
                sql,
                habitacionRowMapper
        );
    }

    public int save(Habitacion habitacion) {
        String sql = "INSERT INTO habitacion (numero, estado, id_tipo, id_sede) VALUES (:numero, :estado, :idTipo, :idSede)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("numero", habitacion.getNumero())
                .addValue("estado", habitacion.getEstado())
                .addValue("idTipo", habitacion.getIdTipo())
                .addValue("idSede", habitacion.getIdSede());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(sql, params, keyHolder, new String[] { "id_habitacion" });

        return keyHolder.getKey().intValue();
    }

    public int update(int id, Habitacion habitacion) {
        String sql = "UPDATE habitacion "
                + "SET numero = :numero, "
                + "estado = :estado, "
                + "id_tipo = :idTipo, "
                + "id_sede = :idSede "
                + "WHERE id_habitacion = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("numero", habitacion.getNumero())
                .addValue("estado", habitacion.getEstado())
                .addValue("idTipo", habitacion.getIdTipo())
                .addValue("idSede", habitacion.getIdSede());

        return namedJdbc.update(sql, params); // devuelve 1 si se actualizó una fila, 0 si no se encontró la habitación
                                              // con ese id
    }

    public int delete(int id) {
        String sql = "DELETE FROM habitacion WHERE id_habitacion = :id";
        return namedJdbc.update(sql, new MapSqlParameterSource("id", id));
    }
    // new MapSqlParameterSource("id", id) → forma corta para un solo parámetro

}
