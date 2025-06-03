package monokai.gymapp.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Aluno {

    private Long alunoId;
    private LocalDate dataNascimento;
    private Double altura;
    private Double peso;
    private Double imc;
    private List<Treino> treinoList;

}
