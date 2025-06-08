package monokai.gymapp.rest;



import monokai.gymapp.domain.model.TarefaItem;
import monokai.gymapp.repository.TarefaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa-item")
public class TarefaItemRest {

    @Autowired
    private TarefaItemRepository tarefaItemRepository;

    @GetMapping
    public List<TarefaItem> findAll(){
        return tarefaItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public TarefaItem findById(@PathVariable Long id) {
        return tarefaItemRepository.findById(id);
    }

    @PostMapping
    public TarefaItem insert(@RequestBody TarefaItem tarefaItem){
        return tarefaItemRepository.insert(tarefaItem);
    }

    @PutMapping("/{id}")
    public TarefaItem update(@RequestBody TarefaItem tarefaItem, @PathVariable Long id){
        return tarefaItemRepository.update(tarefaItem, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        tarefaItemRepository.deleteById(id);
    }
}

