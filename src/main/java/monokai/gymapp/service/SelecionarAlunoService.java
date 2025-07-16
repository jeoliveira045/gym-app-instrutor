package monokai.gymapp.service;

import lombok.AllArgsConstructor;
import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.domain.model.Treino;
import monokai.gymapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelecionarAlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    TarefaRepository tarefaRepository;
    @Autowired
    TarefaItemRepository tarefaItemRepository;
    @Autowired
    ExercicioRepository exercicioRepository;

    public Aluno exec(Long id){
        Aluno aluno = alunoRepository.findById(id);
        List<Treino> treinoList = treinoRepository.findAllByAlunoId(id);
        aluno.setTreinoList(treinoList);
        aluno.getTreinoList().forEach(treino -> {
            var tarefaList = tarefaRepository.findAllByTreino(treino.getTreinoId());
            treino.setTarefaList(tarefaList);
            treino.getTarefaList().forEach(tarefa -> {
                var tarefaItemList = tarefaItemRepository.findByTarefaId(tarefa.getTarefaId());
                tarefa.setTarefaItems(tarefaItemList);
                tarefa.getTarefaItems().forEach(tarefaItem -> {
                    var exercicio = exercicioRepository.findById(tarefaItem.getExercicioId());
                    tarefaItem.setExercicio(exercicio);
                });
            });
        });
        return aluno;
    }
}
