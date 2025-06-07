package monokai.gymapp.rest;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoRest {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        return alunoRepository.findById(id);
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno){
        return alunoRepository.insert(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@RequestBody Aluno aluno, @PathVariable Long id){
        return alunoRepository.update(aluno, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        alunoRepository.deleteAlunoById(id);
    }
}
