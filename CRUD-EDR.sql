USE biblioteca_db;

-- CRUDs que faltaram após o primeiro // Para empréstimo, devolução e reserva
-- CRUD de EMPRÉSTIMO

-- Registrando Empréstimo // Aqui simula que o funcionário registra a retirada do exemplar id_exemplar = 1 pelo usuário id_usuario = 1
INSERT INTO emprestimo (data_emprestimo, data_previsao_devolucao, status, id_usuario, id_exemplar) 
VALUES (NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'ATIVO', 1, 1);

-- Regra de negócio: Atualizar o status do exemplar para EMPRESTADO
UPDATE exemplar 
SET status = 'EMPRESTADO' 
WHERE id_exemplar = 1;


-- Consultando Empréstimos (Detalhado) // aqui consulta empréstimos ativos exibindo nome do usuário, título do livro e código do exemplar
SELECT 
    emp.id_emprestimo,
    u.nome AS usuario,
    u.tipo_usuario,
    l.titulo AS livro,
    ex.codigo_exemplar,
    emp.data_emprestimo,
    emp.data_previsao_devolucao,
    emp.status
FROM emprestimo emp
INNER JOIN usuario u ON emp.id_usuario = u.id_usuario
INNER JOIN exemplar ex ON emp.id_exemplar = ex.id_exemplar
INNER JOIN livro l ON ex.id_livro = l.id_livro
WHERE emp.status = 'ATIVO';


-- Renovando Empréstimo ou Atualizar Status // aqui adiciona mais 7 dias na data prevista de devolução
UPDATE emprestimo 
SET data_previsao_devolucao = DATE_ADD(data_previsao_devolucao, INTERVAL 7 DAY) 
WHERE id_emprestimo = 1;

-- Atualizando status para ATRASADO (caso a data limite tenha passado)
UPDATE emprestimo 
SET status = 'ATRASADO' 
WHERE id_emprestimo = 1 AND data_previsao_devolucao < NOW();


-- Cancelando/Excluindo Registro de Empréstimo // Isso seria usado apenas para erros de digitação. Empréstimos concluídos devem ser atualizados via Devolução.
DELETE FROM emprestimo WHERE id_emprestimo = 1;


-- CRUD de DEVOLUÇÃO
-- Recriando o empréstimo para o fluxo da devolução
INSERT INTO emprestimo (id_emprestimo, data_emprestimo, data_previsao_devolucao, status, id_usuario, id_exemplar) 
VALUES (1, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'ATIVO', 1, 1);


-- Registrando Devolução // O funcionário (id_usuario = 3, especialização funcionário) registra a devolução do empréstimo (id_emprestimo = 1)
INSERT INTO devolucao (data_devolucao, id_emprestimo, id_funcionario) 
VALUES (NOW(), 1, 3);

-- Regra de negócio: Encerrar o empréstimo e liberar o exemplar
UPDATE emprestimo 
SET status = 'CONCLUIDO' 
WHERE id_emprestimo = 1;

UPDATE exemplar 
SET status = 'DISPONIVEL' 
WHERE id_exemplar = 1;


-- Consultando Devoluções Realizadas
SELECT 
    d.id_devolucao,
    d.data_devolucao,
    emp.id_emprestimo,
    u_leitor.nome AS leitor,
    l.titulo AS livro,
    u_func.nome AS funcionario_atendente
FROM devolucao d
INNER JOIN emprestimo emp ON d.id_emprestimo = emp.id_emprestimo
INNER JOIN usuario u_leitor ON emp.id_usuario = u_leitor.id_usuario
INNER JOIN exemplar ex ON emp.id_exemplar = ex.id_exemplar
INNER JOIN livro l ON ex.id_livro = l.id_livro
INNER JOIN usuario u_func ON d.id_funcionario = u_func.id_usuario;


-- Alterarando Informações de Devolução (Ajuste de data/horário)
UPDATE devolucao 
SET data_devolucao = NOW() 
WHERE id_devolucao = 1;


-- Removendo Registro de Devolução
DELETE FROM devolucao WHERE id_devolucao = 1;


-- CRUD de RESERVA

-- Registrando Reserva para um Usuário // Usuário (id_usuario = 1) reserva um livro (id_livro = 1) quando não há exemplares disponíveis
INSERT INTO reserva (data_reserva, status, id_usuario, id_livro) 
VALUES (NOW(), 'PENDENTE', 1, 1);


-- Consultando Reservas Pendentes
SELECT 
    r.id_reserva,
    r.data_reserva,
    r.status,
    u.nome AS usuario,
    u.email,
    l.titulo AS livro
FROM reserva r
INNER JOIN usuario u ON r.id_usuario = u.id_usuario
INNER JOIN livro l ON r.id_livro = l.id_livro
WHERE r.status = 'PENDENTE';


-- Alterando Status da Reserva (Atendida ou Cancelada) // Aqui deve-se marcar como ATENDIDA quando o livro é liberado e o usuário realiza o empréstimo
UPDATE reserva 
SET status = 'ATENDIDA' 
WHERE id_reserva = 1;

-- Marcando como CANCELADA
UPDATE reserva 
SET status = 'CANCELADA' 
WHERE id_reserva = 1;


-- Excluindo Registro de Reserva
DELETE FROM reserva WHERE id_reserva = 1;