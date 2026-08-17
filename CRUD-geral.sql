USE biblioteca_db;

-- CRUD de EDITORA
-- Cadastrando Editoras
INSERT INTO editora (nome, endereco, telefone, email) 
VALUES ('Editora Novatec', 'Rua Vergueiro, 1000 - SP', '(11) 2345-6789', 'contato@novatec.com.br'),
       ('Editora Alta Books', 'Av. Paulista, 500 - SP', '(11) 98765-4321', 'vendas@altabooks.com.br');

-- Consultando Editoras
SELECT * FROM editora;
SELECT * FROM editora WHERE id_editora = 1;

-- Alterarando Editora
UPDATE editora 
SET telefone = '(11) 99999-0000', email = 'atendimento@novatec.com.br' 
WHERE id_editora = 1;

-- Excluindo Editora // Apenas se não houver livro associado
DELETE FROM editora WHERE id_editora = 2;

-- CRUD de CATEGORIA DE LIVRO
-- Cadastrando Categorias
INSERT INTO categoria (nome, descricao) 
VALUES ('Engenharia de Software', 'Livros sobre arquitetura, padrões e desenvolvimento'),
       ('Banco de Dados', 'Livros de SQL, modelagem e administração de SGBDs');

-- Consultando Categorias
SELECT * FROM categoria;
SELECT * FROM categoria WHERE nome LIKE '%Banco%';

-- Alterarando Categoria
UPDATE categoria 
SET descricao = 'Desenvolvimento de software, padrões e boas práticas' 
WHERE id_categoria = 1;

-- Excluindo Categoria
DELETE FROM categoria WHERE id_categoria = 2;

-- CRUD de AUTOR
-- Cadastrando Autores
INSERT INTO autor (nome) 
VALUES ('Robert C. Martin'),
       ('Andrew S. Tanenbaum'),
       ('Martin Fowler');

-- Consultando Autores
SELECT * FROM autor;
SELECT * FROM autor WHERE nome LIKE 'Robert%';

-- Alterando Autor
UPDATE autor 
SET nome = 'Robert Cecil Martin (Uncle Bob)' 
WHERE id_autor = 1;

-- Excluindo Autor
DELETE FROM autor WHERE id_autor = 3;


-- CRUD de LIVRO
-- Cadastrando Livro e vincular com Autor (N:N) // aqui devem existir: id_categoria = 1 e id_editora = 1 
INSERT INTO livro (codigo, titulo, isbn, ano, id_categoria, id_editora) 
VALUES ('LIV-001', 'Código Limpo', '978-8576082675', 2009, 1, 1);

-- Vinculando o Livro (id_livro = 1) ao Autor (id_autor = 1)
INSERT INTO livro_autor (id_livro, id_autor) VALUES (1, 1);

-- Consultando Livros com detalhes da Categoria, Editora e Autores
SELECT 
    l.id_livro,
    l.codigo,
    l.titulo,
    l.isbn,
    l.ano,
    c.nome AS categoria,
    e.nome AS editora,
    a.nome AS autor
FROM livro l
INNER JOIN categoria c ON l.id_categoria = c.id_categoria
INNER JOIN editora e ON l.id_editora = e.id_editora
INNER JOIN livro_autor la ON l.id_livro = la.id_livro
INNER JOIN autor a ON la.id_autor = a.id_autor;

-- Alterarando Livro
UPDATE livro 
SET ano = 2011, titulo = 'Código Limpo: Habilidades Práticas do Agile' 
WHERE id_livro = 1;

-- Excluindo Associação e Livro
DELETE FROM livro_autor WHERE id_livro = 1;
DELETE FROM livro WHERE id_livro = 1;

-- CRUD de EXEMPLAR DE LIVRO // Aqui deve-se assumir que foi criado o livro com id_livro = 1 de novo:
INSERT INTO livro (id_livro, codigo, titulo, isbn, ano, id_categoria, id_editora) 
VALUES (1, 'LIV-001', 'Código Limpo', '978-8576082675', 2009, 1, 1);

-- Cadastrando Exemplares do Livro 1
INSERT INTO exemplar (codigo_exemplar, status, localizacao, id_livro) 
VALUES ('EX-001-A', 'DISPONIVEL', 'Estante A1', 1),
       ('EX-001-B', 'DISPONIVEL', 'Estante A1', 1);

-- Consultando Exemplares e disponibilidade
SELECT 
    e.id_exemplar,
    e.codigo_exemplar,
    e.status,
    e.localizacao,
    l.titulo
FROM exemplar e
INNER JOIN livro l ON e.id_livro = l.id_livro;

-- Alterarando localização ou status do Exemplar
UPDATE exemplar 
SET status = 'MANUTENCAO', localizacao = 'Balcão de Reparo' 
WHERE id_exemplar = 2;

-- Excluindo Exemplar
DELETE FROM exemplar WHERE id_exemplar = 2;


-- CRUD de USUÁRIO (ALUNO, PROFESSOR, FUNCIONÁRIO)

-- Bloco A - USUÁRIO: ALUNO

-- Cadastrando Aluno (Tabela USUARIO + Tabela ALUNO)
INSERT INTO usuario (id_usuario, nome, cpf, email, senha, tipo_usuario, status) 
VALUES (1, 'João Silva', '111.222.333-44', 'joao.aluno@email.com', 'senha123', 'ALUNO', 'ATIVO');

INSERT INTO aluno (id_usuario, matricula, curso, id_responsavel) 
VALUES (1, '2026001', 'Análise e Desenv. de Sistemas', NULL);

-- Consultando Alunos
SELECT u.id_usuario, u.nome, u.cpf, u.email, a.matricula, a.curso, u.status 
FROM usuario u
INNER JOIN aluno a ON u.id_usuario = a.id_usuario;

-- Alterando Dados do Aluno
UPDATE usuario SET email = 'joao.novo@email.com' WHERE id_usuario = 1;
UPDATE aluno SET curso = 'Engenharia de Software' WHERE id_usuario = 1;

-- Excluindo Aluno (ON DELETE CASCADE remove da tabela aluno automaticamente)
-- DELETE FROM usuario WHERE id_usuario = 1;



-- Bloco B - USUÁRIO: PROFESSOR

-- Cadastrando Professor (Tabela USUARIO + Tabela PROFESSOR)
INSERT INTO usuario (id_usuario, nome, cpf, email, senha, tipo_usuario, status) 
VALUES (2, 'Maria Oliveira', '555.666.777-88', 'maria.prof@email.com', 'senha123', 'PROFESSOR', 'ATIVO');

INSERT INTO professor (id_usuario, matricula, departamento) 
VALUES (2, 'PRF-2026', 'Tecnologia da Informação');

-- Consultando Professores
SELECT u.id_usuario, u.nome, u.cpf, u.email, p.matricula, p.departamento, u.status 
FROM usuario u
INNER JOIN professor p ON u.id_usuario = p.id_usuario;

-- Alterarando Professor
UPDATE professor SET departamento = 'Ciência da Computação' WHERE id_usuario = 2;


-- Bloco 3 - USUÁRIO: FUNCIONÁRIO

-- Cadastrando Funcionário (Tabela USUARIO + Tabela FUNCIONARIO)
INSERT INTO usuario (id_usuario, nome, cpf, email, senha, tipo_usuario, status) 
VALUES (3, 'Carlos Souza', '999.888.777-66', 'carlos.func@email.com', 'senha123', 'FUNCIONARIO', 'ATIVO');

INSERT INTO funcionario (id_usuario, cargo) 
VALUES (3, 'Bibliotecário');

-- Consultando Funcionários
SELECT u.id_usuario, u.nome, u.cpf, u.email, f.cargo, u.status 
FROM usuario u
INNER JOIN funcionario f ON u.id_usuario = f.id_usuario;

-- Alterarando Funcionário
UPDATE funcionario SET cargo = 'Bibliotecário Chefe' WHERE id_usuario = 3;

-- Deletando - excluindo Usuário // Remove automaticamente das tabelas filhas
DELETE FROM usuario WHERE id_usuario = 3;

