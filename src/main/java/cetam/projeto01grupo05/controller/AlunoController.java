package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Aluno;
import cetam.projeto01grupo05.model.enums.TipoUsuario;
import cetam.projeto01grupo05.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        aluno.setTipoUsuario(TipoUsuario.ALUNO);
        Aluno novoAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno dadosAtualizados) {
        return alunoRepository.findById(id).map(alunoExistente -> {
            alunoExistente.setNome(dadosAtualizados.getNome());
            alunoExistente.setCpf(dadosAtualizados.getCpf());
            alunoExistente.setEmail(dadosAtualizados.getEmail());
            alunoExistente.setMatricula(dadosAtualizados.getMatricula());
            alunoExistente.setCurso(dadosAtualizados.getCurso());
            alunoExistente.setStatus(dadosAtualizados.getStatus());

            Aluno salvo = alunoRepository.save(alunoExistente);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}