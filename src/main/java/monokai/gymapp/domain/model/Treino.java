package monokai.gymapp.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Treino {

    private Long treinoId;
    private String nomeTreino;
    private Long alunoId;
    private List<Tarefa> tarefaList;

}
