package monokai.gymapp.domain.model;

import lombok.Data;

@Data
public class TarefaItem {
    private Long tarefaitemId;
    private Integer qtdSerie;
    private Integer qtdRepeticoes;
    private Long tarefaId;
    private Long exercicioId;
    private Exercicio exercicio;
}
