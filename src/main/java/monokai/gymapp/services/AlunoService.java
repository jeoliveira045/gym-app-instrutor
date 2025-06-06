package monokai.gymapp.services;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.domain.model.Treino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlunoService {

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
        jdbcTemplate.queryForList(treinoQuery, id).forEach(treinos -> {
            Treino treino = new Treino();
            Arrays.stream(treino.getClass().getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
//                try {
                String fieldName = field.getName();
                String newFieldName = "";
                Pattern pattern = Pattern.compile("[A-Z]");
                List<Character> characterList = getCharacters(fieldName, pattern);
                for(Character chara: characterList){
                    newFieldName += chara.toString();
                }
                fieldName = newFieldName.toLowerCase(Locale.ROOT);
                System.out.println(fieldName);

            });


        });
        return aluno;
    }

    private static List<Character> getCharacters(String fieldName, Pattern pattern) {
        char[] charList = fieldName.toCharArray();
        List<Character> characterList = new ArrayList<>();
        for(char chara: charList){
            characterList.add(chara);
        }
        characterList.forEach(character -> {
            Matcher matcher = pattern.matcher(character.toString());
            if(matcher.find()){
                characterList.add(characterList.indexOf(character), '_');
            }
        });
        return characterList;
    }

}
