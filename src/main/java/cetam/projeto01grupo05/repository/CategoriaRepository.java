package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar categoria pelo nome
    Optional<Categoria> findByNomeIgnoreCase(String nome);

    // Listar categorias que contêm determinado termo
    List<Categoria> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}