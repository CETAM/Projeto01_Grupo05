USE biblioteca_db;

-- Deletando um exemplar específico
DELETE FROM exemplar 
WHERE id_exemplar = 1;

-- Deletando uma editora //só funcionará se não houver livros vinculados a ela
DELETE FROM editora 
WHERE id_editora = 1;