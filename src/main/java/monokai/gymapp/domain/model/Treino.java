package monokai.gymapp.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Treino {

    private Long treinoId;
    private List<Tarefa> tarefaList;

}
