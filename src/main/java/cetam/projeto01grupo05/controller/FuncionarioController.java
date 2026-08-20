package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Funcionario;
import cetam.projeto01grupo05.model.enums.TipoUsuario;
import cetam.projeto01grupo05.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionario) {
        funcionario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario dados) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(dados.getNome());
            funcionario.setCpf(dados.getCpf());
            funcionario.setEmail(dados.getEmail());
            funcionario.setSenha(dados.getSenha());
            funcionario.setStatus(dados.getStatus());
            funcionario.setCargo(dados.getCargo());
            return ResponseEntity.ok(funcionarioRepository.save(funcionario));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}