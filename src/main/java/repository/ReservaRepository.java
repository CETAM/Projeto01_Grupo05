package com.biblioteca.repository;

import com.biblioteca.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Listar todas as reservas ativas/pendentes de um livro em ordem de prioridade (data de criação)
    List<Reserva> findByLivroIdAndStatusOrderByDataSolicitacaoAsc(Long livroId, String status);

    // Buscar reservas pendentes do usuário
    List<Reserva> findByUsuarioIdAndStatus(Long usuarioId, String status);

    // Verificar se o usuário já possui uma reserva ativa para determinado livro
    boolean existsByUsuarioIdAndLivroIdAndStatus(Long usuarioId, Long livroId, String status);

    // Obter a primeira reserva da fila para atender quando um exemplar for devolvido
    Optional<Reserva> findFirstByLivroIdAndStatusOrderByDataSolicitacaoAsc(Long livroId, String status);
}