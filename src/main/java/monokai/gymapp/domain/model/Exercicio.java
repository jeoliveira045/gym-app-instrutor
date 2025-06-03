package monokai.gymapp.domain.model;

import lombok.Data;

@Data
public class Exercicio {
    public Long exercicioId;
    public String nomeExercicio;
    public byte[] imagemExercicio;
}
