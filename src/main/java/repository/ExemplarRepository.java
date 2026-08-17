package com.biblioteca.repository;

import com.biblioteca.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    // Buscar exemplar pelo código de barras/tombamento único
    Optional<Exemplar> findByCodigoEtiqueta(String codigoEtiqueta);

    // Listar todos os exemplares de um determinado livro pelo ID do livro
    List<Exemplar> findByLivroId(Long livroId);

    // Listar apenas os exemplares de um livro que estão disponíveis para empréstimo
    List<Exemplar> findByLivroIdAndStatus(Long livroId, String status);

    // Contar quantos exemplares disponíveis um livro possui no momento
    long countByLivroIdAndStatus(Long livroId, String status);
}