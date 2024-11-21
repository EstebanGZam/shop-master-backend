-- Insertando roles
INSERT INTO roles (name) VALUES ('ADMINISTRADOR');
INSERT INTO roles (name) VALUES ('CLIENTE');

-- Insertando usuarios
INSERT INTO users (username, password, email, address) VALUES ('admin', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'admin@gmail.com', 'Cali');
INSERT INTO users (username, password, email, address) VALUES ('Juan Colonia', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jcolonia@gmail.com', 'Bolo');
INSERT INTO users (username, password, email, address) VALUES ('Esteban Gaviria', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'egaviria@gmail.com', 'Palmira');
INSERT INTO users (username, password, email, address) VALUES ('Juan Diaz', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jdiaz@gmail.com', 'Cali');
INSERT INTO users (username, password, email, address) VALUES ('Manuel Cardona', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'mcardona@gmail.com', 'Palmira');
INSERT INTO users (username, password, email, address) VALUES ('Juan Muñoz', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jmuñoz@gmail.com', 'Cali');

-- Insertando los usuarios y sus roles en la tabla cruzada
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO user_role (user_id, role_id) VALUES (5, 2);
INSERT INTO user_role (user_id, role_id) VALUES (6, 2);

-- Insertando métodos de pago
INSERT INTO payment_methods (name) VALUES ('Tar. Crédito');
INSERT INTO payment_methods (name) VALUES ('Tar. Débito');