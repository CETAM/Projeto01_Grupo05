package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    
    Optional<Exemplar> findByCodigoEtiqueta(String codigoEtiqueta);

    List<Exemplar> findByLivroId(Long livroId);

    List<Exemplar> findByLivroIdAndStatus(Long livroId, StatusExemplar status);

    long countByLivroIdAndStatus(Long livroId, String status);
}