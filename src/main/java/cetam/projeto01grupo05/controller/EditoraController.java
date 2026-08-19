package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Editora;
import cetam.projeto01grupo05.service.EditoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping
    public ResponseEntity<List<Editora>> listarTodos() {
        return ResponseEntity.ok(editoraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable Long id) {
        return editoraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Editora> cadastrar(@RequestBody Editora editora) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.salvar(editora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> atualizar(@PathVariable Long id, @RequestBody Editora dados) {
        return editoraService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return editoraService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}