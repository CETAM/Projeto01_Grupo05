package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Professor;
import cetam.projeto01grupo05.model.enums.TipoUsuario;
import cetam.projeto01grupo05.repository.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        return ResponseEntity.ok(professorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrar(@RequestBody Professor professor) {
        professor.setTipoUsuario(TipoUsuario.PROFESSOR);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorRepository.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor dados) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(dados.getNome());
            professor.setCpf(dados.getCpf());
            professor.setEmail(dados.getEmail());
            professor.setSenha(dados.getSenha());
            professor.setStatus(dados.getStatus());
            professor.setMatricula(dados.getMatricula());
            professor.setDepartamento(dados.getDepartamento());
            return ResponseEntity.ok(professorRepository.save(professor));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}