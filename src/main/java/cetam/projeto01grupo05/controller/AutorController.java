package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Autor;
import cetam.projeto01grupo05.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Autor> cadastrar(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvar(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor dados) {
        return autorService.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return autorService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}