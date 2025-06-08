package monokai.gymapp.repository;

import monokai.gymapp.domain.model.TarefaItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TarefaItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TarefaItem> findAll() {
        String sql = "SELECT tarefaitem_id, qtd_serie, qtd_repeticoes, tarefa_id, exercicio_id FROM tarefaitem";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TarefaItem.class));
    }

    public TarefaItem findById(Long id) {
        String sql = "SELECT tarefaitem_id, qtd_serie, qtd_repeticoes, tarefa_id, exercicio_id FROM tarefaitem WHERE tarefaitem_id = ?";
        Optional<TarefaItem> itemOptional = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TarefaItem.class), id)
                .stream().findAny();

        return itemOptional.orElseThrow();
    }

    public TarefaItem insert(TarefaItem item) {
        String sql = "INSERT INTO tarefaitem (qtd_serie, qtd_repeticoes, tarefa_id, exercicio_id) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.execute(sql, (PreparedStatementCallback<TarefaItem>) ps -> {
            ps.setInt(1, item.getQtdSerie());
            ps.setInt(2, item.getQtdRepeticoes());
            ps.setLong(3, item.getTarefaId());
            ps.setLong(4, item.getExercicioId());

            ps.executeUpdate();
            return item;
        });
    }

    public TarefaItem update(TarefaItem item, Long id) {
        String sql = "UPDATE tarefaitem SET qtd_serie = ?, qtd_repeticoes = ?, tarefa_id = ?, exercicio_id = ? WHERE tarefaitem_id = ?";

        return jdbcTemplate.execute(sql, (PreparedStatementCallback<TarefaItem>) ps -> {
            ps.setInt(1, item.getQtdSerie());
            ps.setInt(2, item.getQtdRepeticoes());
            ps.setLong(3, item.getTarefaId());
            ps.setLong(4, item.getExercicioId());
            ps.setLong(5, id);

            ps.executeUpdate();
            return item;
        });
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM tarefaitem WHERE tarefaitem_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
