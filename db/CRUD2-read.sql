USE biblioteca_db;

-- Consultando todos os livros cadastrados
SELECT * FROM livro;

-- Consultando livros trazendo o nome da Categoria e da Editora (JOIN)
SELECT 
    l.id_livro,
    l.codigo,
    l.titulo,
    l.isbn,
    c.nome AS categoria,
    e.nome AS editora
FROM livro l
INNER JOIN categoria c ON l.id_categoria = c.id_categoria
INNER JOIN editora e ON l.id_editora = e.id_editora;

-- Consultando a disponibilidade dos exemplares de um livro
SELECT 
    e.codigo_exemplar,
    e.status,
    e.localizacao,
    l.titulo
FROM exemplar e
INNER JOIN livro l ON e.id_livro = l.id_livro
WHERE e.status = 'DISPONIVEL';