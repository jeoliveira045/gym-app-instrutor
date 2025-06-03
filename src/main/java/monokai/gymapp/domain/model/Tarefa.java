package monokai.gymapp.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Tarefa {

    public Long tarefaId;
    public String nomeTarefa;
    public String tipoTarefa;
    public List<TarefaItem> tarefaItems;
}
