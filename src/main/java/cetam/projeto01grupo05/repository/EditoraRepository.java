package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    List<Editora> findByNomeContainingIgnoreCase(String nome);

    Optional<Editora> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}
