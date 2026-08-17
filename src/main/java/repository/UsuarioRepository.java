package com.biblioteca.repository;

import com.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuário pelo e-mail (geralmente usado no Login/Autenticação)
    Optional<Usuario> findByEmail(String email);

    // Verificar se já existe um cadastro com determinado CPF ou e-mail
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    // Buscar usuários por parte do nome
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    // Listar apenas usuários ativos no sistema
    List<Usuario> findByAtivoTrue();
}