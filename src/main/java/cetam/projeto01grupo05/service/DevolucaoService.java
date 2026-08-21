package cetam.projeto01grupo05.service;



import cetam.projeto01grupo05.model.*;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class DevolucaoService {
    private final DevolucaoRepository devolucaoRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;

    public DevolucaoService(DevolucaoRepository devolucaoRepository, EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository) {
        this.devolucaoRepository = devolucaoRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

    @Transactional
    public Devolucao registrarDevolucao(Long idEmprestimo, Funcionario funcionario) {
        Emprestimo emp = emprestimoRepository.findById(idEmprestimo).orElseThrow();
        emp.setStatus(StatusEmprestimo.CONCLUIDO);

        Exemplar ex = emp.getExemplar();
        ex.setStatus(StatusExemplar.DISPONIVEL);
        exemplarRepository.save(ex);

        Devolucao dev = new Devolucao();
        dev.setEmprestimo(emp);
        dev.setFuncionario(funcionario);
        dev.setDataDevolucao(LocalDateTime.now());

        return devolucaoRepository.save(dev);
    }
}