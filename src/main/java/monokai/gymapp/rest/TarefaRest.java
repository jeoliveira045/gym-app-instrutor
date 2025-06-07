package monokai.gymapp.rest;

import monokai.gymapp.domain.model.Tarefa;
import monokai.gymapp.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaRest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> findAll(){
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa findById(@PathVariable Long id) {
        return tarefaRepository.findById(id);
    }

    @PostMapping
    public Tarefa insert(@RequestBody Tarefa tarefa){
        return tarefaRepository.insert(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa update(@RequestBody Tarefa tarefa, @PathVariable Long id){
        return tarefaRepository.update(tarefa, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        tarefaRepository.deleteTarefaById(id);
    }
}
