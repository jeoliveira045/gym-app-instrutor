package monokai.gymapp.domain.model;

import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Data
public class Aluno {

    private Long alunoId;
    private LocalDate dataNascimento;
    private String nome;
    private Double altura;
    private Double peso;
    private Double imc;
    private List<Treino> treinoList;

    public Double getImc() {
        this.imc = peso / (altura * altura);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String numeroFormatado = decimalFormat.format(this.imc);
        return Double.parseDouble(numeroFormatado.replace(",","."));
    }
}
