package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Devolucao;
import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.Funcionario;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.DevolucaoRepository;
import cetam.projeto01grupo05.repository.EmprestimoRepository;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DevolucaoService {

    private final DevolucaoRepository devolucaoRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;

    public DevolucaoService(
            DevolucaoRepository devolucaoRepository,
            EmprestimoRepository emprestimoRepository,
            ExemplarRepository exemplarRepository) {

        this.devolucaoRepository = devolucaoRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

    @Transactional
    public Devolucao registrarDevolucao(Long idEmprestimo, Funcionario funcionario) {

        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);

        Exemplar exemplar = emprestimo.getExemplar();
        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        exemplarRepository.save(exemplar);
        emprestimoRepository.save(emprestimo);

        Devolucao devolucao = new Devolucao();
        devolucao.setEmprestimo(emprestimo);
        devolucao.setFuncionario(funcionario);
        devolucao.setDataDevolucao(LocalDateTime.now());

        return devolucaoRepository.save(devolucao);
    }
}
