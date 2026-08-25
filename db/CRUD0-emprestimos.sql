USE biblioteca_db;

-- Registrando um Empréstimo
INSERT INTO emprestimo (data_emprestimo, data_previsao_devolucao, status, id_usuario, id_exemplar)
VALUES (NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'ATIVO', 1, 1);

-- Marcando o Exemplar como EMPRESTADO
UPDATE exemplar SET status = 'EMPRESTADO' WHERE id_exemplar = 1;

-- Registrando a Devolução do Empréstimo 1
INSERT INTO devolucao (data_devolucao, id_emprestimo, id_funcionario)
VALUES (NOW(), 1, 1);

-- Finalizando o Empréstimo e devolver o Exemplar para DISPONIVEL
UPDATE emprestimo SET status = 'CONCLUIDO' WHERE id_emprestimo = 1;
UPDATE exemplar SET status = 'DISPONIVEL' WHERE id_exemplar = 1;