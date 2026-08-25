--  Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS grupo05_db;
USE grupo05_db;

-- Criação da Tabela USUARIO
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('ALUNO', 'PROFESSOR', 'FUNCIONARIO') NOT NULL,
    status ENUM('ATIVO', 'BLOQUEADO', 'INATIVO') DEFAULT 'ATIVO'
);

-- Criação das especializações de Usuário (aluno, professor, funcionário)
CREATE TABLE IF NOT EXISTS aluno (
    id_usuario BIGINT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    curso VARCHAR(100) NOT NULL,
    id_responsavel BIGINT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_responsavel) REFERENCES aluno(id_usuario)
);

CREATE TABLE IF NOT EXISTS professor (
    id_usuario BIGINT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    departamento VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS funcionario (
    id_usuario BIGINT PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Criação da Tabela CATEGORIA
CREATE TABLE IF NOT EXISTS categoria (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT
);

-- Criação da Tabela EDITORA
CREATE TABLE IF NOT EXISTS editora (
    id_editora BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

-- Criação da Tabela AUTOR
CREATE TABLE IF NOT EXISTS autor (
    id_autor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Criação da Tabela LIVRO
CREATE TABLE IF NOT EXISTS livro (
    id_livro BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    ano INT,
    id_categoria BIGINT NOT NULL,
    id_editora BIGINT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_editora) REFERENCES editora(id_editora)
);

-- Criação da Tabela N:N LIVRO_AUTOR
CREATE TABLE IF NOT EXISTS livro_autor (
    id_livro BIGINT NOT NULL,
    id_autor BIGINT NOT NULL,
    PRIMARY KEY (id_livro, id_autor),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro) ON DELETE CASCADE,
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor) ON DELETE CASCADE
);

-- Criação da Tabela EXEMPLAR
CREATE TABLE IF NOT EXISTS exemplar (
    id_exemplar BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_exemplar VARCHAR(30) NOT NULL UNIQUE,
    status ENUM('DISPONIVEL', 'EMPRESTADO', 'RESERVADO', 'MANUTENCAO') DEFAULT 'DISPONIVEL',
    localizacao VARCHAR(50),
    id_livro BIGINT NOT NULL,
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro) ON DELETE CASCADE
);

-- Criação da Tabela EMPRESTIMO
CREATE TABLE IF NOT EXISTS emprestimo (
    id_emprestimo BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATETIME NOT NULL,
    data_previsao_devolucao DATETIME NOT NULL,
    status ENUM('ATIVO', 'CONCLUIDO', 'ATRASADO') DEFAULT 'ATIVO',
    id_usuario BIGINT NOT NULL,
    id_exemplar BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_exemplar) REFERENCES exemplar(id_exemplar)
);

-- Criação da Tabela DEVOLUCAO
CREATE TABLE IF NOT EXISTS devolucao (
    id_devolucao BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_devolucao DATETIME NOT NULL,
    id_emprestimo BIGINT NOT NULL UNIQUE,
    id_funcionario BIGINT NOT NULL,
    FOREIGN KEY (id_emprestimo) REFERENCES emprestimo(id_emprestimo),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_usuario)
);

-- Criação da Tabela RESERVA
CREATE TABLE IF NOT EXISTS reserva (
    id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_reserva DATETIME NOT NULL,
    status ENUM('PENDENTE', 'ATENDIDA', 'CANCELADA') DEFAULT 'PENDENTE',
    id_usuario BIGINT NOT NULL,
    id_livro BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);