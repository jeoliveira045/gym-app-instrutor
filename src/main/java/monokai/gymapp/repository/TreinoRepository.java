package monokai.gymapp.repository;

import monokai.gymapp.domain.model.Tarefa;
import monokai.gymapp.domain.model.Treino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TreinoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Treino> findAll(){
        var treino = "SELECT treino_id, nome_treino, aluno_id FROM TREINO";

        return jdbcTemplate.query(treino, new BeanPropertyRowMapper<>(Treino.class));
    }

    public Treino findById(Long id){
        var query = "SELECT treino_id, nome_treino, aluno_id FROM TREINO WHERE treino_id = ?";

        Optional<Treino> treinoOptional = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Treino.class), id).stream().findAny();

        return treinoOptional.orElseThrow();
    }

    public Treino insert(Treino treino){
        var query = "INSERT INTO TREINO(nome_treino, aluno_id) VALUES (?, ?)";

        return jdbcTemplate.execute(query, (PreparedStatementCallback<Treino>) ps -> {
            ps.setString(1, treino.getNomeTreino());
            ps.setLong(2, treino.getAlunoId());

            ps.executeUpdate();

            return treino;
        });
    }

    public Treino update(Treino treino, Long id){
        var query = "UPDATE TREINO SET nome_treino = ? WHERE treino_id = ?;";

        return jdbcTemplate.execute(query, (PreparedStatementCallback<Treino>) ps -> {
            ps.setString(1, treino.getNomeTreino());
            ps.setLong(2, id);

            ps.executeUpdate();

            return treino;
        });
    }

    public void delete(Long id){
        var query = "DELETE FROM TREINO WHERE treino_id = ?";

        jdbcTemplate.update(query, id);
    }
}
