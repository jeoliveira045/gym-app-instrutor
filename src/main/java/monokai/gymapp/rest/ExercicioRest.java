package monokai.gymapp.rest;

import monokai.gymapp.domain.model.Exercicio;
import monokai.gymapp.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercicio")
public class ExercicioRest {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping
    public List<Exercicio> findAll(){
        return exercicioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Exercicio findById(@PathVariable Long id) {
        return exercicioRepository.findById(id);
    }

    @PostMapping
    public Exercicio insert(@RequestBody Exercicio exercicio){
        return exercicioRepository.insert(exercicio);
    }

    @PutMapping("/{id}")
    public Exercicio update(@RequestBody Exercicio exercicio, @PathVariable Long id){
        return exercicioRepository.update(exercicio, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        exercicioRepository.deleteById(id);
    }
}
