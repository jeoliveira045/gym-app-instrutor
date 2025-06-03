package monokai.gymapp.services;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.domain.model.Treino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Aluno> findAll(){

        return jdbcTemplate.query(
                """
                SELECT 
                    aluno_id, nome, data_nascimento, altura, peso, imc 
                FROM ALUNO""", new BeanPropertyRowMapper<>(Aluno.class)).stream().map(aluno -> {
                    List<Treino> treinoList = jdbcTemplate.query("""
                            SELECT * FROM TREINO WHERE ALUNO_ID = ?
                            """, new Object[]{aluno.getAlunoId()}, (rs, rowNum) -> {
                                    var treino = new Treino();
                                    treino.setTreinoId(rs.getLong("treino_id"));
                                    treino.setNomeTreino(rs.getString("nome_treino"));
                                    return treino;
                    });
                    aluno.setTreinoList(treinoList);
                    return aluno;
        }).toList();
    }

}
