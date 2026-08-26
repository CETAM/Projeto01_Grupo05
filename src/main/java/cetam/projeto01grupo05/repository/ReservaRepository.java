package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByLivroIdLivroAndStatusOrderByDataReservaAsc(
            Long idLivro,
            StatusReserva status
    );

    List<Reserva> findByUsuarioIdUsuarioAndStatus(
            Long idUsuario,
            StatusReserva status
    );

    boolean existsByUsuarioIdUsuarioAndLivroIdLivroAndStatus(
            Long idUsuario,
            Long idLivro,
            StatusReserva status
    );

    Optional<Reserva> findFirstByLivroIdLivroAndStatusOrderByDataReservaAsc(
            Long idLivro,
            StatusReserva status
    );

    List<Reserva> findByUsuario(Usuario usuario);
}