package monokai.gymapp.domain.model;

import lombok.Data;

@Data
public class Exercicio {
    public Long exercicioId;
    public String nomeExercicio;
    public String tipo;
    public String musculo;
    public String equipamento;
    public String dificuldade;
    public String instrucoes;
    public byte[] imagemExercicio;
}
