package monokai.gymapp.domain.model;

import lombok.Data;

@Data
public class TarefaItem {
    public Long tarefaItemId;
    public Integer qtdSeries;
    public Integer qtdRepeticoes;
    public Exercicio exercicio;
}
