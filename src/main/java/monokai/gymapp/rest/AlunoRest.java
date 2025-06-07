package monokai.gymapp;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoRest {

    @Autowired
    private AlunoRepository alunoService;

    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        return alunoService.findById(id);
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno){
        return alunoService.insert(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@RequestBody Aluno aluno, @PathVariable Long id){
        return alunoService.update(aluno, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        alunoService.deleteAlunoById(id);
    }
}
