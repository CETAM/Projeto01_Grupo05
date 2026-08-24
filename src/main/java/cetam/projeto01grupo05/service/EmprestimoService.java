package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.EmprestimoRepository;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import cetam.projeto01grupo05.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            ExemplarRepository exemplarRepository,
            UsuarioRepository usuarioRepository) {

        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    // Lista somente os empréstimos de um determinado usuário
    public List<Emprestimo> listarPorUsuario(Long idUsuario) {
        return emprestimoRepository.findByUsuarioIdAndDataDevolucaoIsNull(idUsuario);
    }

    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    @Transactional
    public Emprestimo realizarEmprestimo(
            Long idUsuario,
            Long idExemplar,
            int dias) {

        Usuario usuario = usuarioRepository
                .findById(idUsuario)
                .orElseThrow();

        Exemplar exemplar = exemplarRepository
                .findById(idExemplar)
                .orElseThrow();

        exemplar.setStatus(StatusExemplar.EMPRESTADO);
        exemplarRepository.save(exemplar);

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplar);
        emprestimo.setDataEmprestimo(LocalDateTime.now());

        emprestimo.setDataPrevisaoDevolucao(
                LocalDateTime.now().plusDays(dias)
        );

        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        return emprestimoRepository.save(emprestimo);
    }
}