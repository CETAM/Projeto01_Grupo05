package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Categoria;
import cetam.projeto01grupo05.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> atualizar(Long id, Categoria dados) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(dados.getNome());
            categoria.setDescricao(dados.getDescricao());
            return categoriaRepository.save(categoria);
        });
    }

    public boolean deletar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
