package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    Optional<Devolucao> findByEmprestimoIdEmprestimo(Long idEmprestimo);
}

