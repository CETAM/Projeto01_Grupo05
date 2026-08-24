package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    // Buscar exemplar pelo código
    Optional<Exemplar> findByCodigoExemplar(String codigoExemplar);

    // Listar exemplares de um determinado livro
    List<Exemplar> findByLivroIdLivro(Long idLivro);

    // Listar exemplares de um livro por status
    List<Exemplar> findByLivroIdLivroAndStatus(
            Long idLivro,
            StatusExemplar status
    );

    // Contar exemplares de um livro por status
    long countByLivroIdLivroAndStatus(
            Long idLivro,
            StatusExemplar status
    );
}
