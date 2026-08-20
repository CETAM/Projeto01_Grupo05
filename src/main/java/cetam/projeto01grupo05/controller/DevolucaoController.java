package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Devolucao;
import cetam.projeto01grupo05.service.DevolucaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devolucoes")
public class DevolucaoController {

    private final DevolucaoService devolucaoService;

    public DevolucaoController(DevolucaoService devolucaoService) {
        this.devolucaoService = devolucaoService;
    }

    @GetMapping
    public ResponseEntity<List<Devolucao>> listarTodos() {
        return ResponseEntity.ok(devolucaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucao> buscarPorId(@PathVariable Long id) {
        return devolucaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devolucao> cadastrar(@RequestBody Devolucao devolucao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(devolucaoService.salvar(devolucao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucao> atualizar(@PathVariable Long id, @RequestBody Devolucao dados) {
        return devolucaoService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return devolucaoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}