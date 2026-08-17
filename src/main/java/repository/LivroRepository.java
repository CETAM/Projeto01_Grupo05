package com.biblioteca.repository;

import com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Buscar livro pelo código ISBN
    Optional<Livro> findByIsbn(String isbn);

    // Buscar livros por parte do título
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Listar todos os livros disponíveis para empréstimo
    List<Livro> findByDisponivelTrue();

    // Listar todos os livros de um autor específico pelo ID do autor
    List<Livro> findByAutorId(Long autorId);

    // Filtrar livros por gênero/categoria
    List<Livro> findByCategoriaIgnoreCase(String categoria);
}