-- Inserir dados de teste
INSERT INTO workspaces (name, capacity, location, active) 
SELECT 'Espaço Beta', 20, 'Bloco A - 1º Andar', true
WHERE NOT EXISTS (SELECT 1 FROM workspaces WHERE name = 'Espaço Beta');

INSERT INTO workspaces (name, capacity, location, active) 
SELECT 'Espaço Gamma', 30, 'Bloco B - 2º Andar', true
WHERE NOT EXISTS (SELECT 1 FROM workspaces WHERE name = 'Espaço Gamma');

INSERT INTO workspaces (name, capacity, location, active) 
SELECT 'Espaço Delta', 15, 'Bloco A - 2º Andar', true
WHERE NOT EXISTS (SELECT 1 FROM workspaces WHERE name = 'Espaço Delta');

-- Inserir usuários de teste (incluindo LIBRARIAN) - só se não existirem
INSERT INTO users_app (name, email, role) 
SELECT 'João Silva', 'joao@example.com', 'STUDENT'
WHERE NOT EXISTS (SELECT 1 FROM users_app WHERE email = 'joao@example.com');

INSERT INTO users_app (name, email, role) 
SELECT 'Maria Santos', 'maria@example.com', 'STUDENT'
WHERE NOT EXISTS (SELECT 1 FROM users_app WHERE email = 'maria@example.com');

INSERT INTO users_app (name, email, role) 
SELECT 'Admin User', 'admin@example.com', 'LIBRARIAN'
WHERE NOT EXISTS (SELECT 1 FROM users_app WHERE email = 'admin@example.com');

-- Atualizar usuário existente para ser LIBRARIAN se já existir
UPDATE users_app SET role = 'LIBRARIAN' WHERE email = 'mbarros46@users.noreply.github.com';

-- Inserir algumas reservas de exemplo - só se as salas existirem
INSERT INTO reservations (workspace_id, user_id, start_at, end_at, status, created_at) 
SELECT r.id, u.id, '2025-09-22 17:00:00+00', '2025-09-22 21:00:00+00', 'PENDING', CURRENT_TIMESTAMP
FROM workspaces r, users_app u 
WHERE r.name = 'Espaço Beta' AND u.email = 'joao@example.com'
AND NOT EXISTS (SELECT 1 FROM reservations WHERE workspace_id = r.id AND start_at = '2025-09-22 17:00:00+00');

INSERT INTO reservations (workspace_id, user_id, start_at, end_at, status, created_at) 
SELECT r.id, u.id, '2025-09-23 09:00:00+00', '2025-09-23 11:00:00+00', 'APPROVED', CURRENT_TIMESTAMP
FROM workspaces r, users_app u 
WHERE r.name = 'Espaço Gamma' AND u.email = 'maria@example.com'
AND NOT EXISTS (SELECT 1 FROM reservations WHERE workspace_id = r.id AND start_at = '2025-09-23 09:00:00+00');

INSERT INTO reservations (workspace_id, user_id, start_at, end_at, status, created_at) 
SELECT r.id, u.id, '2025-09-24 14:00:00+00', '2025-09-24 16:00:00+00', 'PENDING', CURRENT_TIMESTAMP
FROM workspaces r, users_app u 
WHERE r.name = 'Espaço Delta' AND u.email = 'joao@example.com'
AND NOT EXISTS (SELECT 1 FROM reservations WHERE workspace_id = r.id AND start_at = '2025-09-24 14:00:00+00');