package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> cadastrar(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.salvar(emprestimo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable Long id, @RequestBody Emprestimo dados) {
        return emprestimoService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return emprestimoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}