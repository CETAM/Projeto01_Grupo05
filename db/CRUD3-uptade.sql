USE biblioteca_db;

-- Atualizando o e-mail e telefone de uma Editora
UPDATE editora 
SET email = 'atendimento@altabooks.com.br', 
    telefone = '(11) 99999-8888'
WHERE id_editora = 1;

-- Atualizando o status de um Exemplar para EMPRESTADO
UPDATE exemplar 
SET status = 'EMPRESTADO' 
WHERE id_exemplar = 1;

-- Atualizando o status de um usuário para BLOQUEADO
UPDATE usuario 
SET status = 'BLOQUEADO' 
WHERE id_usuario = 1;