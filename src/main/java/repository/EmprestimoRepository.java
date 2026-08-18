package com.biblioteca.repository;

import com.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Buscar todos os empréstimos ativos de um determinado usuário
    List<Emprestimo> findByUsuarioIdAndDataDevolucaoIsNull(Long usuarioId);

    // Listar empréstimos em atraso (data prev. devolução anterior à data informada e sem data de devolução)
    List<Emprestimo> findByDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(LocalDate dataAtual);

    // Buscar o histórico de empréstimos de um exemplar específico
    List<Emprestimo> findByExemplarId(Long exemplarId);

    // Verificar se um usuário possui algum empréstimo em aberto e atrasado
    boolean existsByUsuarioIdAndDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(Long usuarioId, LocalDate dataAtual);
}