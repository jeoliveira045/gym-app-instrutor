package monokai.gymapp;

import monokai.gymapp.domain.model.Aluno;
import monokai.gymapp.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoRest {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        return alunoService.findById(id);
    }
}
