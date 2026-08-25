package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {

    private final ExemplarRepository exemplarRepository;

    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    public Exemplar salvar(Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    public List<Exemplar> listarTodos() {
        return exemplarRepository.findAll();
    }

    public List<Exemplar> buscarDisponiveisPorLivro(Long idLivro) {
        return exemplarRepository.findByLivroIdLivroAndStatus(
                idLivro,
                StatusExemplar.DISPONIVEL
        );
    }

    public Optional<Exemplar> buscarPorId(Long id) {
        return exemplarRepository.findById(id);
    }

    public Optional<Exemplar> atualizar(Long id, Exemplar dados) {
        return exemplarRepository.findById(id).map(exemplar -> {
            exemplar.setCodigoExemplar(dados.getCodigoExemplar());
            exemplar.setStatus(dados.getStatus());
            exemplar.setLocalizacao(dados.getLocalizacao());
            exemplar.setLivro(dados.getLivro());

            return exemplarRepository.save(exemplar);
        });
    }

    public boolean deletar(Long id) {
        if (exemplarRepository.existsById(id)) {
            exemplarRepository.deleteById(id);
            return true;
        }
        return false;
    }
}