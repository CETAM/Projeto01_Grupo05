package com.biblioteca.repository;

import com.biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    // Buscar editora pelo nome (exato ou parcial)
    List<Editora> findByNomeContainingIgnoreCase(String nome);

    // Verificar se já existe editora cadastrada com determinado CNPJ
    Optional<Editora> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}