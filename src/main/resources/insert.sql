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

-- Insertando primera orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-03-15 10:30:00', 79.98, 3);

-- Detalle orden 1
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 49.99, 1, '001');

-- Detalle orden 1
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 29.99, 1, '002');

-- Detalles de pago con tarjeta de crédito para primera orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (123456, 'Esteban', 'Gaviria', '123', '2025-12-31 00:00:00', 1, 1);


-- Factura para primera orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-03-15 10:31:00', 79.98, 1);

-- Insertando segunda orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-03-20 15:45:00', 119.98, 3);

-- Detalle orden 2
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (2, 59.99, 2, '003');

-- Detalles de pago con tarjeta de débito para segunda orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (789012, 'Esteban', 'Gaviria', '456', '2026-06-30 00:00:00',2,2);

-- Factura para segunda orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-03-20 15:46:00', 119.98, 2);
