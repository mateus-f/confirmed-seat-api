-- 1. POPULANDO USUÁRIOS (Diversidade de nomes e regiões)
INSERT INTO users (name, email, cpf, password, role) VALUES
('Ricardo Almeida Santos', 'ricardo.admin@empresa.com.br', '12345678901', 'hash_admin_1', 'ADMIN'),
('Alessandra Volpato', 'ale.volpato@gestao.com.br', '56789012345', 'hash_admin_2', 'ADMIN'),
('Ana Julia Pereira', 'ana.pereira@gmail.com', '98765432100', 'hash_pass_1', 'CUSTOMER'),
('Marcos Vinícius Silva', 'marcos.v@outlook.com.br', '45678912344', 'hash_pass_2', 'CUSTOMER'),
('Beatriz Souza Lima', 'beatriz.sl@yahoo.com.br', '32165498711', 'hash_pass_3', 'CUSTOMER'),
('Thiago Oliveira', 'thiago.oliveira@uol.com.br', '78912345688', 'hash_pass_4', 'CUSTOMER'),
('Enzo Gabriel Ferreira', 'enzo.gabriel@bol.com.br', '23456789012', 'hash_pass_5', 'CUSTOMER'),
('Maria Eduarda Santos', 'madu.santos@gmail.com', '34567890123', 'hash_pass_6', 'CUSTOMER'),
('Raimundo Nonato Costa', 'nonato.ceara@terra.com.br', '45678901234', 'hash_pass_7', 'CUSTOMER'),
('Jefferson Willian Silva', 'jeff.ws@protonmail.com', '67890123456', 'hash_pass_8', 'CUSTOMER'),
('Cláudia Raia de Souza', 'claudinha.souza@ig.com.br', '78901234567', 'hash_pass_9', 'CUSTOMER'),
('Yuri Takahashi', 'yuri.taka@gmail.com', '89012345678', 'hash_pass_10', 'CUSTOMER'),
('Larissa Manoela Costa', 'larissa.m@gmail.com', '90123456789', 'hash_pass_11', 'CUSTOMER'),
('João Pedro Cavalcanti', 'jp.cavalcanti@hotmail.com', '01234567890', 'hash_pass_12', 'CUSTOMER'),
('Fernanda Montenegro Medeiros', 'fernanda.m@globo.com', '11223344556', 'hash_pass_13', 'CUSTOMER'),
('Wilson Simoninha Junior', 'wilson.sim@uol.com.br', '22334455667', 'hash_pass_14', 'CUSTOMER'),
('Tati Quebra Barraco Silva', 'tati.barraco@gmail.com', '33445566778', 'hash_pass_15', 'CUSTOMER'),
('Aline Wirley Coêlho', 'aline.wirley@outlook.com', '44556677889', 'hash_pass_16', 'CUSTOMER'),
('Douglas Silva Santos', 'dg.silva@gmail.com', '55667788990', 'hash_pass_17', 'CUSTOMER'),
('Paolla Oliveira Real', 'paolla.real@terra.com.br', '66778899001', 'hash_pass_18', 'CUSTOMER');

-- 2. POPULANDO EVENTOS (Datas em 2026)
INSERT INTO events (title, description, event_date, capacity, location, status) VALUES
('Show de Samba - Rio de Janeiro', 'Noite de samba no Circo Voador.', '2026-11-15 20:00:00-03', 1500, 'Lapa, Rio de Janeiro - RJ', 'OPEN_FOR_BOOKING'),
('Workshop Java & Spring Boot v4', 'Imersão técnica para desenvolvedores.', '2026-06-10 09:00:00-03', 200, 'Av. Paulista, São Paulo - SP', 'OPEN_FOR_BOOKING'),
('Festival de Gastronomia Paraense', 'O melhor do Norte: Tacacá e Açaí.', '2026-08-20 12:00:00-03', 3000, 'Estação das Docas, Belém - PA', 'OPEN_FOR_BOOKING'),
('Summit de IA 2026', 'Conferência sobre Inteligência Artificial.', '2026-10-05 08:00:00-03', 1200, 'Expocenter Norte, São Paulo - SP', 'OPEN_FOR_BOOKING'),
('Oktoberfest Blumenau', 'Tradição alemã em Santa Catarina.', '2026-10-07 19:00:00-03', 15000, 'Vila Germânica, Blumenau - SC', 'OPEN_FOR_BOOKING'),
('Carnaval de Olinda 2026', 'Blocos de rua e frevo histórico.', '2026-02-14 10:00:00-03', 50000, 'Sítio Histórico, Olinda - PE', 'FINISHED'),
('Fórum de Cibersegurança', 'Proteção de dados e LGPD na prática.', '2026-05-12 14:00:00-03', 400, 'Centro de Convenções, Brasília - DF', 'OPEN_FOR_BOOKING'),
('Maratona de Curitiba', 'Corrida de rua pelas ruas da capital.', '2026-11-22 06:00:00-03', 2000, 'Centro Cívico, Curitiba - PR', 'OPEN_FOR_BOOKING');

-- 3. POPULANDO RESERVAS (Simulando diversas interações)
-- Nota: gen_random_uuid() funciona no PostgreSQL 13+. Caso use versão anterior, substitua por UUIDs fixos.

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'CONFIRMED', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Show de Samba - Rio de Janeiro'
AND u.email IN ('ana.pereira@gmail.com', 'marcos.v@outlook.com.br', 'tati.barraco@gmail.com', 'dg.silva@gmail.com');

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'CONFIRMED', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Workshop Java & Spring Boot v4'
AND u.email IN ('jeff.ws@protonmail.com', 'yuri.taka@gmail.com', 'jp.cavalcanti@hotmail.com');

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'WAITING', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Workshop Java & Spring Boot v4'
AND u.email IN ('beatriz.sl@yahoo.com.br', 'larissa.m@gmail.com');

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'CONFIRMED', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Festival de Gastronomia Paraense'
AND u.email IN ('nonato.ceara@terra.com.br', 'madu.santos@gmail.com', 'paolla.real@terra.com.br', 'fernanda.m@globo.com');

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'CONFIRMED', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Summit de IA 2026'
AND u.email IN ('yuri.taka@gmail.com', 'jeff.ws@protonmail.com', 'ale.volpato@gestao.com.br');

INSERT INTO bookings (status, confirmation_code, event_id, user_id)
SELECT 'CONFIRMED', gen_random_uuid(), e.id, u.id
FROM events e, users u
WHERE e.title = 'Fórum de Cibersegurança'
AND u.email IN ('ricardo.admin@empresa.com.br', 'claudinha.souza@ig.com.br', 'enzo.gabriel@bol.com.br');

-- Inserindo alguns cancelamentos específicos para testar lógica de negócio
INSERT INTO bookings (status, confirmation_code, event_id, user_id, cancelled_at)
VALUES
('CANCELLED', gen_random_uuid(),
  (SELECT id FROM events WHERE title = 'Show de Samba - Rio de Janeiro'),
  (SELECT id FROM users WHERE email = 'thiago.oliveira@uol.com.br'),
  '2026-04-01 15:30:00-03'),
('CANCELLED', gen_random_uuid(),
  (SELECT id FROM events WHERE title = 'Maratona de Curitiba'),
  (SELECT id FROM users WHERE email = 'aline.wirley@outlook.com'),
  '2026-04-10 09:00:00-03');