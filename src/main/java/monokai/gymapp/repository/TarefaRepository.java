package monokai.gymapp.repository;

import monokai.gymapp.domain.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TarefaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Tarefa> findAll(){
        var tarefa = "SELECT tarefa_id, nome_tarefa, tipo_tarefa, treino_id FROM tarefa";
        

        return jdbcTemplate.query(tarefa, new BeanPropertyRowMapper<>(Tarefa.class));
    }

    public Tarefa findById(Long id){
        var query = "SELECT tarefa_id, nome_tarefa, tipo_tarefa, treino_id FROM tarefa WHERE tarefa_id = ?";

        Optional<Tarefa> tarefaOptional = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Tarefa.class), id).stream().findAny();

        return tarefaOptional.orElseThrow();
    }

    public Tarefa insert(Tarefa tarefa){
        var query = "INSERT INTO tarefa(nome_tarefa, tipo_tarefa, treino_id)  VALUES (?,?,?)";

        return jdbcTemplate.execute(query, (PreparedStatementCallback<Tarefa>) ps -> {
            ps.setString(1, tarefa.getNomeTarefa());
            ps.setString(2, tarefa.getTipoTarefa());
            ps.setLong(3, tarefa.getTreinoId());

            ps.executeUpdate();

            return tarefa;
        });


    }

    public Tarefa update(Tarefa tarefa, Long id){
        var query = "UPDATE TAREFA SET nome_tarefa = ?, tipo_tarefa = ?, treino_id = ? where tarefa_id = ?";

        return jdbcTemplate.execute(query, (PreparedStatementCallback<Tarefa>) ps -> {
            ps.setString(1, tarefa.getNomeTarefa());
            ps.setString(2, tarefa.getTipoTarefa());
            ps.setLong(3, tarefa.getTreinoId());
            ps.setLong(4, id);

            ps.executeUpdate();

            return tarefa;
        });
    }

    public void deleteTarefaById(Long id){
        var query = "DELETE FROM TAREFA WHERE tarefa_id = ?";

        jdbcTemplate.update(query, id);
    }
}
