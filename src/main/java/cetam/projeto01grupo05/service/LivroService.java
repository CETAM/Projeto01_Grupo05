package cetam.projeto01grupo05.service;



import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) { return livroRepository.save(livro); }
    public List<Livro> listarTodos() { return livroRepository.findAll(); }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Livro dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}