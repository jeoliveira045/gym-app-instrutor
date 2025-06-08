package monokai.gymapp.repository;

import monokai.gymapp.domain.model.Exercicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class ExercicioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Exercicio> findAll() {
        String sql = "SELECT exercicio_id, nome_exercicio, tipo, musculo, equipamento, dificuldade, instrucoes, imagem_exercicio FROM exercicio";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exercicio.class));
    }

    public Exercicio findById(Long id) {
        String sql = "SELECT exercicio_id, nome_exercicio, tipo, musculo, equipamento, dificuldade, instrucoes, imagem_exercicio FROM exercicio WHERE exercicio_id = ?";
        Optional<Exercicio> exercicioOptional = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exercicio.class), id)
                .stream().findAny();

        return exercicioOptional.orElseThrow();
    }

    public Exercicio insert(Exercicio exercicio) {
        String sql = "INSERT INTO exercicio (nome_exercicio, tipo, musculo, equipamento, dificuldade, instrucoes, imagem_exercicio) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Exercicio>) ps -> {
            ps.setString(1, exercicio.nomeExercicio);
            ps.setString(2, exercicio.tipo);
            ps.setString(3, exercicio.musculo);
            ps.setString(4, exercicio.equipamento);
            ps.setString(5, exercicio.dificuldade);
            ps.setString(6, exercicio.instrucoes);
            ps.setBytes(7, exercicio.imagemExercicio);

            ps.executeUpdate();
            return exercicio;
        });
    }

    public Exercicio update(Exercicio exercicio, Long id) {
        String sql = "UPDATE exercicio SET nome_exercicio = ?, tipo = ?, musculo = ?, equipamento = ?, dificuldade = ?, instrucoes = ?, imagem_exercicio = ? " +
                "WHERE exercicio_id = ?";

        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Exercicio>) ps -> {
            ps.setString(1, exercicio.nomeExercicio);
            ps.setString(2, exercicio.tipo);
            ps.setString(3, exercicio.musculo);
            ps.setString(4, exercicio.equipamento);
            ps.setString(5, exercicio.dificuldade);
            ps.setString(6, exercicio.instrucoes);
            ps.setBytes(7, exercicio.imagemExercicio);
            ps.setLong(8, id);

            ps.executeUpdate();
            return exercicio;
        });
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM exercicio WHERE exercicio_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
