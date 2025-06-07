package monokai.gymapp.repository;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.domain.model.Treino;
import monokai.gymapp.utils.MappingFieldTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class AlunoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Aluno> findAll(){

        return jdbcTemplate.query(
                """
                SELECT 
                    aluno_id, nome, data_nascimento, altura, peso, imc 
                FROM ALUNO""", new BeanPropertyRowMapper<>(Aluno.class));
    }

    public Aluno findById(Long id){
        String alunoquery = """
                SELECT 
                    aluno_id, nome, data_nascimento, altura, peso, imc 
                FROM ALUNO WHERE ALUNO_ID = ?
                """;
        String treinoQuery = """
                SELECT
                    treino_id, nome_treino from
                TREINO WHERE aluno_id = ?
                """;
        var aluno = jdbcTemplate.queryForObject(alunoquery,  new BeanPropertyRowMapper<>(Aluno.class), id);
        List<Treino> treinoList = new ArrayList<>();
        jdbcTemplate.queryForList(treinoQuery, id).forEach(treinos -> {
            Treino treino = new Treino();
            Arrays.stream(treino.getClass().getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                String fieldName = getFieldName(field.getName());
                try {
                    var mapping = new MappingFieldTypes();
                    var mappingFunction = mapping.getFunctionMap().get(field.getType());
                    if(mappingFunction != null){
                        field.set(treino, mappingFunction.apply(treinos.get(fieldName)));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            treinoList.add(treino);
        });
        assert aluno != null;
        aluno.setTreinoList(treinoList);
        return aluno;
    }

    public Aluno insert(Aluno aluno){
        String selectAluno = """
                INSERT INTO ALUNO(NOME, DATA_NASCIMENTO, ALTURA, PESO, IMC) VALUES (?,?,?,?,?)
                """;

        jdbcTemplate.execute(selectAluno, (PreparedStatement ps) -> {
            ps.setString(1, aluno.getNome());
            ps.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            ps.setDouble(3, aluno.getAltura());
            ps.setDouble(4, aluno.getPeso());
            ps.setDouble(5, aluno.getImc());

            return ps.executeUpdate();
        });

        return aluno;
    }

    public Aluno update(Aluno aluno, Long id){
        String selectAluno = """
                UPDATE ALUNO 
                SET NOME = ?,
                DATA_NASCIMENTO = ?,
                ALTURA = ?,
                PESO = ?,
                IMC = ?
                WHERE ALUNO_ID = ?
                """;

        jdbcTemplate.execute(selectAluno, (PreparedStatement ps) -> {
            ps.setLong(6, aluno.getAlunoId());
            ps.setString(1, aluno.getNome());
            ps.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            ps.setDouble(3, aluno.getAltura());
            ps.setDouble(4, aluno.getPeso());
            ps.setDouble(5, aluno.getImc());

            return ps.executeUpdate();
        });
        return aluno;
    }

    public int deleteAlunoById(Long alunoId) {
        String sql = "DELETE FROM aluno WHERE aluno_id = ?";
        return jdbcTemplate.update(sql, alunoId);
    }

    private static String getFieldName(String fieldName) {
        Pattern pattern = Pattern.compile("[A-Z]");
        String newFieldName = "";
        List<String> characterList = new ArrayList<>(fieldName.chars().mapToObj(c -> String.valueOf((char) c)).toList());
        List<Integer> indexes = getIndexes(characterList, pattern);
        indexes.forEach(index -> {
            characterList.add(index, "_");
        });
        for(String chara: characterList){
            newFieldName += chara;
        }
        fieldName = newFieldName.toLowerCase(Locale.ROOT);
        return fieldName;
    }

    private static List<Integer> getIndexes(List<String> characterList, Pattern pattern) {
        List<Integer> indexes = new ArrayList<>();
        characterList.forEach(character -> {
            Matcher matcher = pattern.matcher(character);
            if(matcher.find()){
                int index = characterList.indexOf(character);
                indexes.add(index);
            }
        });
        return indexes;
    }
}
