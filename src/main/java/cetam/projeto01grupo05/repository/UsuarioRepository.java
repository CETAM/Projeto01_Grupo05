package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    List<Usuario> findByStatus(
            cetam.projeto01grupo05.model.enums.StatusUsuario status
    );
}