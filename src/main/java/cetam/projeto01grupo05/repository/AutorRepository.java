package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autor por nome exato ou contendo trecho
    List<Autor> findByNomeContainingIgnoreCase(String nome);


    // Verificar se o autor já existe no sistema antes de cadastrar
    Optional<Autor> findByNomeIgnoreCase(String nome);
}