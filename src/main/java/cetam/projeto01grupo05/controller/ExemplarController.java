package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.service.ExemplarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exemplares")
public class ExemplarController {

    private final ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @GetMapping
    public ResponseEntity<List<Exemplar>> listarTodos() {
        return ResponseEntity.ok(exemplarService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exemplar> buscarPorId(@PathVariable Long id) {
        return exemplarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exemplar> cadastrar(@RequestBody Exemplar exemplar) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exemplarService.salvar(exemplar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exemplar> atualizar(@PathVariable Long id, @RequestBody Exemplar dados) {
        return exemplarService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return exemplarService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}