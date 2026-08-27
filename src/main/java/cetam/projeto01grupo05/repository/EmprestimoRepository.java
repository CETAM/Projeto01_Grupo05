package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Empréstimos de um determinado usuário
    List<Emprestimo> findByUsuarioIdUsuario(Long idUsuario);

    // Empréstimos ativos de um determinado usuário
    List<Emprestimo> findByUsuarioIdUsuarioAndStatus(
            Long idUsuario,
            StatusEmprestimo status
    );

    // Empréstimos de um determinado exemplar
    List<Emprestimo> findByExemplarIdExemplar(Long idExemplar);

    // Empréstimos atrasados pela data prevista de devolução
    List<Emprestimo> findByDataPrevisaoDevolucaoBefore(
            java.time.LocalDateTime data
    );

    // Verifica se o usuário possui empréstimo com determinado status
    boolean existsByUsuarioIdUsuarioAndStatus(
            Long idUsuario,
            StatusEmprestimo status
    );

    // Busca os empréstimos passando o objeto Usuario inteiro
    List<Emprestimo> findByUsuario(Usuario usuario);
}