package cetam.projeto01grupo05.service;

import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExemplarService {
    private final ExemplarRepository exemplarRepository;

    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    public Exemplar salvar(Exemplar exemplar) { return exemplarRepository.save(exemplar); }
    public List<Exemplar> buscarDisponiveisPorLivro(Long idLivro) {
        return exemplarRepository.findByLivroIdAndStatus(idLivro, String.valueOf(StatusExemplar.DISPONIVEL));
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Exemplar dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}