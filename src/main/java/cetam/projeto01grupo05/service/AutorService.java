package cetam.projeto01grupo05.service;

import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Autor;
import cetam.projeto01grupo05.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) { return autorRepository.save(autor); }
    public List<Autor> listarTodos() { return autorRepository.findAll(); }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Autor dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}