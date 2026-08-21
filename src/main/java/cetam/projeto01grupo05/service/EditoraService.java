package cetam.projeto01grupo05.service;


import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Editora;
import cetam.projeto01grupo05.repository.EditoraRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EditoraService {
    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public Editora salvar(Editora editora) { return editoraRepository.save(editora); }
    public List<Editora> listarTodas() { return editoraRepository.findAll(); }

    public List<Editora> listarTodos() {
        return null;
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Editora dados) {
        return null;
    }
}