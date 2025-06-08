package monokai.gymapp.rest;

import monokai.gymapp.domain.model.Treino;
import monokai.gymapp.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treino")
public class TreinoRest {

    @Autowired
    private TreinoRepository treinoRepository;

    @GetMapping
    public List<Treino> findAll(){
        return treinoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Treino findById(@PathVariable Long id) {
        return treinoRepository.findById(id);
    }

    @PostMapping
    public Treino insert(@RequestBody Treino treino){
        return treinoRepository.insert(treino);
    }

    @PutMapping("/{id}")
    public Treino update(@RequestBody Treino treino, @PathVariable Long id){
        return treinoRepository.update(treino, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        treinoRepository.delete(id);
    }
}
