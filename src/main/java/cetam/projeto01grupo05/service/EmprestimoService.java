package cetam.projeto01grupo05.service;



import cetam.projeto01grupo05.model.*;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Emprestimo realizarEmprestimo(Long idUsuario, Long idExemplar, int dias) {
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow();
        Exemplar ex = exemplarRepository.findById(idExemplar).orElseThrow();

        ex.setStatus(StatusExemplar.EMPRESTADO);
        exemplarRepository.save(ex);

        Emprestimo emp = new Emprestimo();
        emp.setUsuario(u);
        emp.setExemplar(ex);
        emp.setDataEmprestimo(LocalDateTime.now());
        emp.setDataPrevisaoDevolucao(LocalDateTime.now().plusDays(dias));
        emp.setStatus(StatusEmprestimo.ATIVO);

        return emprestimoRepository.save(emp);
    }
}