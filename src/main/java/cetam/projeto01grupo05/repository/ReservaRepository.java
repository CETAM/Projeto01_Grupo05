package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Reservas de um determinado livro por status
    List<Reserva> findByLivroIdLivroAndStatusOrderByDataReservaAsc(
            Long idLivro,
            StatusReserva status
    );

    // Reservas de um determinado usuário por status
    List<Reserva> findByUsuarioIdUsuarioAndStatus(
            Long idUsuario,
            StatusReserva status
    );

    // Verifica se o usuário já possui uma reserva para determinado livro
    boolean existsByUsuarioIdUsuarioAndLivroIdLivroAndStatus(
            Long idUsuario,
            Long idLivro,
            StatusReserva status
    );

    // Primeira reserva da fila de um livro
    Optional<Reserva> findFirstByLivroIdLivroAndStatusOrderByDataReservaAsc(
            Long idLivro,
            StatusReserva status
    );
}
