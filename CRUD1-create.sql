USE biblioteca_db;

-- Cadastrando uma Editora
INSERT INTO editora (nome, endereco, telefone, email) 
VALUES ('Editora Alta Books', 'Rua das Flores, 123 - SP', '(11) 98765-4321', 'contato@altabooks.com.br');

-- Cadastrando uma Categoria
INSERT INTO categoria (nome, descricao) 
VALUES ('Tecnologia', 'Livros de computação, desenvolvimento e banco de dados');

-- Cadastrando um Autor
INSERT INTO autor (nome) 
VALUES ('Lúcio Fernando');

-- Cadastrando um Livro (Vinculado à Categoria 1 e Editora 1)
INSERT INTO livro (codigo, titulo, isbn, ano, id_categoria, id_editora) 
VALUES ('LIV-001', 'Aprendendo MySQL na Prática', '978-8576082675', 2024, 1, 1);

-- Vinculando Livro ao Autor na tabela N:N
INSERT INTO livro_autor (id_livro, id_autor) 
VALUES (1, 1);

-- Cadastrando um Exemplar do Livro
INSERT INTO exemplar (codigo_exemplar, status, localizacao, id_livro) 
VALUES ('EX-001-A', 'DISPONIVEL', 'Estante A1', 1);

-- Cadastrando um Usuário (Funcionário)
INSERT INTO usuario (nome, cpf, email, senha, tipo_usuario, status) 
VALUES ('Carlos Eduardo', '123.456.789-00', 'carlos@biblioteca.com', '123456', 'FUNCIONARIO', 'ATIVO');

INSERT INTO funcionario (id_usuario, cargo) 
VALUES (1, 'Bibliotecário Chefe');


