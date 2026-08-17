package com.biblioteca.repository;

import com.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autor por nome exato ou contendo trecho
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    // Buscar autor com base na nacionalidade
    List<Autor> findByNacionalidadeIgnoreCase(String nacionalidade);

    // Verificar se o autor já existe no sistema antes de cadastrar
    Optional<Autor> findByNomeIgnoreCase(String nome);
}