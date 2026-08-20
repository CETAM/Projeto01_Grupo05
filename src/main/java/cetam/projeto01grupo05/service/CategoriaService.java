package cetam.projeto01grupo05.service;

import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Categoria;
import cetam.projeto01grupo05.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria) { return categoriaRepository.save(categoria); }
    public List<Categoria> listarTodas() { return categoriaRepository.findAll(); }

    public void listarTodos() {
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Categoria dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}
